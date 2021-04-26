package com.didispace.att.task;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.didispace.att.dao.ClassAttHistoryMapper;
import com.didispace.att.dao.ClassTableMapper;
import com.didispace.att.dao.StAttMapper;
import com.didispace.att.dao.StudentClassMapper;
import com.didispace.att.entity.ClassTable;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedTask {

  @Autowired
  private ClassTableMapper classTableMapper;

  @Autowired
  private StAttMapper stAttMapper;

  @Autowired
  private StudentClassMapper studentClassMapper;

  @Autowired
  private ClassAttHistoryMapper classAttHistoryMapper;

  private final DecimalFormat format = new DecimalFormat("0.00"); // two digits after dot

  @Transactional
  @Scheduled(cron = "10 0 17 * * MON-FRI") // scheduled at every 17:00 on weekdays (attempted option)
  public void taskUpdateYouhuiquan() {
    try {
      LocalDateTime createTime = LocalDateTime.now();
      LocalDateTime today0Time = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MIN); // midnight of cur day
      List<ClassTable> list = classTableMapper.findAll(); // get all classes
      if (!CollectionUtils.isEmpty(list)) {
        for (ClassTable ct : list) {
          Long id = ct.getId();

          int totalStudents = studentClassMapper.countByClassId(id);
          int totalMarks = stAttMapper.countByClassIdOfTime(id, today0Time);
          int attRate = 0;
          if (totalStudents != 0) {
            String attRateStr = format.format((float) totalMarks / totalStudents);
            // keep two digits after dot and convert into a int from 0-100
            attRate = Double.valueOf(new Double(attRateStr) * 100).intValue();
          }

          classAttHistoryMapper.save(id, totalMarks, totalStudents, attRate, createTime);

        }
      }
      log.info("scheduled class attendance report start：{}", new Date());
    } catch (Exception e) {
      log.error("scheduled class attendance report error", e);
    }
  }
}
