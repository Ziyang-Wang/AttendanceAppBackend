package com.didispace.att.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.att.common.Result;
import com.didispace.att.dto.StAttDto;
import com.didispace.att.entity.StAtt;
import com.didispace.att.service.StAttService;
import com.didispace.att.vo.StAttVo;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/st/att")
public class StAttController {

  @Autowired
  private StAttService stAttService;


  /**
   * get a student basic info and att rate (task1)
   *
   * @param stId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/st/all/{stId}")
  public Result<?> stInfoAttRate(@PathVariable Long stId) throws JsonProcessingException {
    Map<String, Object> map = stAttService.stInfoAttRate(stId);
    return Result.succeedData(map);
  }

  /**
   * student mark attendance (task2)
   *
   * @param stAtt
   * @return
   * @throws JsonProcessingException
   */
  @PostMapping("/mark")
  public Result<?> mark(@RequestBody StAtt stAtt) throws JsonProcessingException {
    LocalDateTime now = LocalDateTime.now();
    stAtt.setCreateTime(now);
    boolean flag = stAttService.save(stAtt);
    return flag ? Result.succeed() : Result.failed();
  }

  /**
   * get all classes and their corr att rates (task3)
   *
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/allclass/curtime")
  public Result<?> findAllClassesWithAttOfCurTime() throws JsonProcessingException {
    List<Map<String, Object>> allList = stAttService.findByAllClassIdOfCurTime();
    return Result.succeedData(allList);
  }

  /**
   * get all students in a class with their basic info and att rate (task4)
   *
   * @param classId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/student/curtime/{classId}")
  public Result<?> findAllStuInfoByClassId(@PathVariable Long classId)
      throws JsonProcessingException {
    List<Map<String, Object>> allList = stAttService.stInfoByClassId(classId);
    return Result.succeedData(allList);
  }

  // additional attempt below

  /**
   * get a class and its att rate at current time
   *
   * @param classId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/class/curtime/{classId}")
  public Result<?> findByClassIdOfCurTime(@PathVariable Long classId)
      throws JsonProcessingException {
    Map<String, Object> map = stAttService.findByClassIdOfCurTime(classId);
    return Result.succeedData(map);
  }


  /**
   * check student attendance details with start and end time specification
   *
   * @param dto
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/all/list")
  public Result<?> findall(StAttDto dto) throws JsonProcessingException {
    List<StAttVo> allList = stAttService.findAll(dto);
    return Result.succeedData(allList);
  }

}
