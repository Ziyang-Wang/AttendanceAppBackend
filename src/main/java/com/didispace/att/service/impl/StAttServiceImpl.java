package com.didispace.att.service.impl;

import com.didispace.att.dao.ClassTableMapper;
import com.didispace.att.entity.ClassTable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.att.dao.StAttMapper;
import com.didispace.att.dao.StudentClassMapper;
import com.didispace.att.dao.SysUserMapper;
import com.didispace.att.dto.StAttDto;
import com.didispace.att.entity.StAtt;
import com.didispace.att.entity.StudentClass;
import com.didispace.att.entity.SysUser;
import com.didispace.att.service.StAttService;
import com.didispace.att.vo.StAttVo;

@Service
public class StAttServiceImpl implements StAttService {

  @Autowired
  private StAttMapper stAttMapper;

  @Autowired
  private StudentClassMapper studentClassMapper;

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private ClassTableMapper classTableMapper;

  private DecimalFormat format = new DecimalFormat("0.00"); // keep two digits after dot

  @Override
  public List<StAttVo> findAll(StAttDto stAtt) {
    Long startTime = stAtt.getStartTime() == null ? 0l : stAtt.getStartTime();
    Long endTime = stAtt.getEndTime() == null ? System.currentTimeMillis() : stAtt.getEndTime();
    return stAttMapper.buildConditionForStAttSql(stAtt.getStId(), stAtt.getClassId(),
        LocalDateTime.ofInstant(new Date(startTime).toInstant(), ZoneId.systemDefault()),
        LocalDateTime.ofInstant(new Date(endTime).toInstant(), ZoneId.systemDefault()));
  }


  @Override
  public Map<String, Object> findByClassIdOfCurTime(Long classId) {
    LocalDateTime createTime = LocalDateTime.now();
    LocalDateTime today0Time = LocalDateTime
        .of(createTime.toLocalDate(), LocalTime.MIN); // midnight of cur day
    int totalStudents = studentClassMapper.countByClassId(classId);
    int totalMarks = stAttMapper.countByClassIdOfTime(classId, today0Time);
    int attRate = 0;
    if (totalStudents != 0) {
      String attRateStr = format.format((float) totalMarks / totalStudents);
      // keep two digits after dot and convert into a int from 0-100
      attRate = Double.valueOf(new Double(attRateStr) * 100).intValue();
    }

    Map<String, Object> map = new HashMap<>();
    String className = classTableMapper.findById(classId).getName();
    map.put("classId", classId);
    map.put("className", className);
    map.put("totalStudents", totalStudents);
    map.put("totalMarks", totalMarks);
    map.put("attRate", attRate);
    return map;
  }

  @Override
  public List<Map<String, Object>> findByAllClassIdOfCurTime() {
    List<ClassTable> classTable = classTableMapper.findAll();
    List<Map<String, Object>> result = new ArrayList<>();
    classTable.forEach(e -> {
      Long classId = e.getId();
      Map<String, Object> map = findByClassIdOfCurTime(classId);
      result.add(map);
    });
    return result;
  }

  @Override
  public boolean save(StAtt stAtt) {
    // only once per day
    Long stId = stAtt.getStId(); // student id
    if (stId == null) {
      return false;
    }
    SysUser sysUser = sysUserMapper.findById(stId);
    if (sysUser == null) {
      return false; // not exist
    }
    if (sysUser.getRole() != 0) {
      return false; // only allow student
    }
    StudentClass stc = studentClassMapper.findById(stId); // get class info by student id
    if (stc == null) {
      return false; // not in a class
    }

    LocalDateTime now = LocalDateTime.now();

    // only allows from 9 to 17 (attempted option)
//    LocalDateTime today9Time = now.withHour(9).withMinute(0).withSecond(0).withNano(0); // 9
//    LocalDateTime today17Time = now.withHour(17).withMinute(0).withSecond(0).withNano(0); // 17
//    if (now.isBefore(today9Time) || now.isAfter(today17Time)
//        || now.getDayOfWeek() == DayOfWeek.SATURDAY
//        || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
//      return false;
//    }

    LocalDateTime today0Time = LocalDateTime
        .of(now.toLocalDate(), LocalTime.MIN); // midnight of cur day
    int count = stAttMapper.countByStIdOfTime(stId, today0Time);
    if (count > 0) {
      return false; // already marked
    }

    stAtt.setAttMark(1);
    stAtt.setClassId(stc.getClassId());
    stAtt.setCreateTime(now);

    int i = stAttMapper.save(stAtt.getStId(), stAtt.getClassId(), 1,
        stAtt.getCreateTime() == null ? LocalDateTime.now() : stAtt.getCreateTime());
    return i > 0;
  }


  @Override
  public Map<String, Object> stInfoAttRate(Long stId) {
    SysUser su = sysUserMapper.findById(stId);
    if (su != null) {
      LocalDateTime ct = su.getCreateTime();
      // count total days since in the system
      int daysNum = (int) (LocalDateTime.now().toLocalDate().toEpochDay() - ct.toLocalDate()
          .toEpochDay()) + 1;
      // count total att days
      int count = stAttMapper.countByStIdOfTime(stId,
          LocalDateTime.ofInstant(new Date(0l).toInstant(), ZoneId.systemDefault()));
      int attRate = 0;
      if (daysNum != 0) {
        String attRateStr = format.format((float) count / daysNum);
        // keep two digits after dot and convert into a int from 0-100
        attRate = Double.valueOf(new Double(attRateStr) * 100).intValue();
      }
      Map<String, Object> map = new HashMap<>();
      map.put("id", su.getId());
      Long cId = studentClassMapper.findById(su.getId()).getClassId();
      String className = classTableMapper.findById(cId).getName();
      map.put("studentName", su.getName());
      map.put("className", className);
      map.put("userName", su.getUsername());
      map.put("email", su.getEmail());
      map.put("pid", su.getPid());
      map.put("createTime", su.getCreateTime());
      map.put("attRate", attRate);
      return map;
    }
    return null;
  }

  @Override
  public List<Map<String, Object>> stInfoByClassId(Long cId) {
    List<StudentClass> studentClassList = studentClassMapper.findByClassId(cId);
    List<Map<String, Object>> result = new ArrayList<>();
    studentClassList.forEach(e -> {
      Long stId = e.getStId();
      Map<String, Object> map = stInfoAttRate(stId);
      result.add(map);
    });
    return result;
  }


}
