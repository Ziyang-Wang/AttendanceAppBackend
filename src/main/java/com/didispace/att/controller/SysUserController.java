package com.didispace.att.controller;


import com.didispace.att.common.CommonErrorCode;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import com.didispace.att.entity.ClassAttHistory;
import com.didispace.att.entity.SysUser;
import com.didispace.att.service.SysUserService;
import com.didispace.att.vo.ClassTableVo;
import com.didispace.att.vo.StAttVo;
import com.fasterxml.jackson.core.JsonProcessingException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class SysUserController {

  @Autowired
  private SysUserService sysUserService;

  /**
   * login (task0)
   *
   * @param req
   * @return
   * @throws JsonProcessingException
   */
  @PostMapping("/login")
  public Result<?> login(@RequestBody SysUser req) throws JsonProcessingException {
    String username = req.getUsername();
    String password = req.getPassword();
    SysUser sysUser = sysUserService.findByName(username);
    if (sysUser != null && sysUser.getPassword().equals(password)) {
      Map<String, Object> map = new HashMap<>();
      map.put("userId", sysUser.getId());
      map.put("role", sysUser.getRole());
      return Result.succeedData(map);
    }
    return Result.failed(CommonErrorCode.USER_PWD_ERROR);
  }

  /**
   * Get overall
   *
   * @param teId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/list/countAll/{teId}")
  public Result<?> findAllAttCount(@PathVariable Long teId) throws JsonProcessingException {
    Map<String, Object> map = sysUserService.findAllAttCount(teId);
    return Result.succeedData(map);
  }

  /**
   * get the classes the teacher in charge of
   *
   * @param teId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/list/{teId}")
  public Result<?> findAllOfTeId(@PathVariable Long teId) throws JsonProcessingException {
    List<ClassTableVo> allList = sysUserService.findAllOfTeId(teId);
    return Result.succeedData(allList);
  }


  /**
   * get the att info rates of classes the teacher in charge of
   *
   * @param teId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/list/count/{teId}")
  public Result<?> findAllAttCountOfTeId(@PathVariable Long teId) throws JsonProcessingException {
    Map<String, Object> map = sysUserService.findAllAttCountOfTeId(teId);
    return Result.succeedData(map);
  }

  /**
   * all students attendance info of a class
   *
   * @param classId
   * @return
   * @throws JsonProcessingException
   */
  @GetMapping("/list/detail/of/{classId}")
  public Result<?> findDetailOfClassId(@PathVariable Long classId) throws JsonProcessingException {
    List<StAttVo> voList = sysUserService.stAttListOfClassIdToday(classId);
    return Result.succeedData(voList);
  }

  /**
   * find class attendance history of a class
   *
   * @param classId
   * @return
   */
  @GetMapping("/list/history/{classId}")
  public Result<?> findAllOfClassId(@PathVariable Long classId) {
    List<ClassAttHistory> list = sysUserService.findAllOfClassId(classId);
    return Result.succeedData(list);
  }

  /**
   * utility method
   *
   * @param sysUser
   * @return
   * @throws JsonProcessingException
   */
  @PostMapping("/save")
  public Result<?> save(@RequestBody SysUser sysUser) throws JsonProcessingException {
    LocalDateTime now = LocalDateTime.now();
    sysUser.setCreateTime(now);
    boolean flag = sysUserService.save(sysUser);
    return flag ? Result.succeed() : Result.failed();
  }

  /**
   * utility method
   *
   * @param id
   * @return
   */
  @PostMapping("/delete/{id}")
  public Result<?> delete(@PathVariable Long id) {
    boolean flag = sysUserService.delById(id);
    return flag ? Result.succeed() : Result.failed();
  }

  /**
   * utility method
   *
   * @param username
   * @return
   */
  @PostMapping("/get/{username}")
  public Result<?> get(@PathVariable String username) {
    SysUser sysUser = sysUserService.findByName(username);
    return Result.succeedData(sysUser);
  }
}

