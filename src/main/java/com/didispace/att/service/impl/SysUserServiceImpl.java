package com.didispace.att.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.didispace.att.dao.ClassAttHistoryMapper;
import com.didispace.att.dao.ClassTableMapper;
import com.didispace.att.dao.StAttMapper;
import com.didispace.att.dao.StudentClassMapper;
import com.didispace.att.dao.SysUserMapper;
import com.didispace.att.entity.ClassAttHistory;
import com.didispace.att.entity.StAtt;
import com.didispace.att.entity.StudentClass;
import com.didispace.att.entity.SysUser;
import com.didispace.att.service.SysUserService;
import com.didispace.att.vo.ClassTableVo;
import com.didispace.att.vo.StAttVo;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ClassTableMapper classTableMapper;

	@Autowired
	private StudentClassMapper studentClassMapper;
	
	@Autowired
	private ClassAttHistoryMapper classAttHistoryMapper;

	@Autowired
	private StAttMapper stAttMapper;

	private DecimalFormat format = new DecimalFormat("0.00"); // two digits after dot

	@Override
	public SysUser findByName(String username) {
		return sysUserMapper.findByName(username);
	}

	@Override
	public boolean save(SysUser sysUser) {
		int i = sysUserMapper.save(sysUser.getUsername(), sysUser.getPassword(), sysUser.getName(),
				sysUser.getStatus(), sysUser.getRole(), sysUser.getEmail(), sysUser.getPid(), sysUser.getCreateTime());
		return i > 0;
	}

	@Override
	public boolean delById(Long id) {
		int i = sysUserMapper.delById(id);
		return i > 0;
	}

	@Override
	public List<SysUser> findAll() {
		return sysUserMapper.findAll();
	}

	@Override
	public List<ClassTableVo> findAllOfTeId(Long teId) {
		return classTableMapper.findAllOfTeId(teId);
	}

	@Override
	public Map<String,Object> findAllAttCount(Long teId) {
		// get all classes in the system
		// (literally regardless of teId, only used for return format)
		List<ClassTableVo> voList = classTableMapper.findAllClass(teId);
		if (!CollectionUtils.isEmpty(voList)) {

			List<Long> sets = voList.stream().map(ClassTableVo::getId)
					.collect(Collectors.toList());

			int totalStudents = studentClassMapper.countByClassIds(sets);
			LocalDateTime createTime = LocalDateTime.now();
			LocalDateTime today0Time = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MIN); // midnight of cur day
			int totalMarks = stAttMapper.countByClassIdsOfTime(sets, today0Time);
			int attRate = 0;
			if (totalStudents != 0) {
				String attRateStr = format.format((float) totalMarks / totalStudents);
				// keep two digits after dot and convert into a int from 0-100
				attRate = Double.valueOf(new Double(attRateStr)*100).intValue();
			}
			Map<String,Object> result = new HashMap<>();
			result.put("totalStudents", totalStudents);
			result.put("totalMarks", totalMarks);
			result.put("attRate", attRate);
			return result;
		}
		return null;
	}
	
	@Override
	public Map<String,Object> findAllAttCountOfTeId(Long teId) {
		// get the class the teacher in charge of
		List<ClassTableVo> voList = classTableMapper.findAllOfTeId(teId);
		if (!CollectionUtils.isEmpty(voList)) {
			
			List<Long> sets = voList.stream().map(p -> p.getId())
					.collect(Collectors.toList());

			int totalStudents = studentClassMapper.countByClassIds(sets);
			// get total attendance(marks)
			LocalDateTime createTime = LocalDateTime.now();
			LocalDateTime today0Time = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MIN); // midnight of cur day

			int totalMarks = stAttMapper.countByClassIdsOfTime(sets, today0Time);
			int attRate = 0;
			if (totalStudents != 0) {
				String attRateStr = format.format((float) totalMarks / totalStudents);
				// keep two digits after dot and convert into a int from 0-100
				attRate = Double.valueOf(new Double(attRateStr)*100).intValue();
			}
			Map<String,Object> result = new HashMap<>();
			result.put("totalStudents", totalStudents);
			result.put("totalMarks", totalMarks);
			result.put("attRate", attRate);
			return result;
		}
		return null;
	}

	
	@Override
	public List<StAttVo> stAttListOfClassIdToday(Long classId) {
		// get the class stu att info
		List<StudentClass> list = studentClassMapper.findByClassId(classId);
		if(!CollectionUtils.isEmpty(list)) {
			List<Long> sets = list.stream().map(p -> p.getStId())
					.collect(Collectors.toList());
			
			List<SysUser> sysUserList = sysUserMapper.findByIds(sets);

			LocalDateTime createTime = LocalDateTime.now();
			LocalDateTime today0Time = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MIN); // midnight of cur day
			Map<Long, StAtt> map = new HashMap<>();
			List<StAtt> stAttList = stAttMapper.findByClassId(classId, today0Time);
			for(StAtt s1 : stAttList) {
				map.put(s1.getStId(), s1);
			}
			List<StAttVo> voList = new ArrayList<>();
			// iterate through student info
			for(SysUser s: sysUserList) {
				StAttVo vo = new StAttVo();
				BeanUtils.copyProperties(s, vo);
				vo.setAttMark(0);
				Long id = s.getId();
				StAtt st = map.get(id);
				if(st != null) {
					vo.setAttMark(1);
					vo.setCreateTime(st.getCreateTime());
				}
				vo.setClassId(classId);
				voList.add(vo);
			}
			return voList;
		}
		return null;
	}
	
	@Override
	public List<ClassAttHistory> findAllOfClassId(Long classId) {
		return classAttHistoryMapper.findAllOfClassId(classId);
	}
}
