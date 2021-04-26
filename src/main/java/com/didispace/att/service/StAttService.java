package com.didispace.att.service;

import java.util.List;
import java.util.Map;

import com.didispace.att.dto.StAttDto;
import com.didispace.att.entity.StAtt;
import com.didispace.att.vo.StAttVo;

public interface StAttService {

  public Map<String, Object> stInfoAttRate(Long stId);


  public boolean save(StAtt stAtt);


  public List<StAttVo> findAll(StAttDto stAtt);


  public Map<String, Object> findByClassIdOfCurTime(Long classId);


  public List<Map<String, Object>> findByAllClassIdOfCurTime();


  public List<Map<String, Object>> stInfoByClassId(Long cId);
}
