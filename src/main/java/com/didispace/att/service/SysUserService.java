package com.didispace.att.service;

import java.util.List;
import java.util.Map;

import com.didispace.att.entity.ClassAttHistory;
import com.didispace.att.entity.SysUser;
import com.didispace.att.vo.ClassTableVo;
import com.didispace.att.vo.StAttVo;

public interface SysUserService {

	public List<SysUser> findAll();

	public SysUser findByName(String username);
	
	public boolean save(SysUser sysUser);
	
	public boolean delById(Long id);

	public List<ClassTableVo> findAllOfTeId(Long teId);
	

	public Map<String,Object> findAllAttCountOfTeId(Long teId);


	public Map<String,Object> findAllAttCount(Long teId);
	

	public List<StAttVo> stAttListOfClassIdToday(Long classId);
	

	public List<ClassAttHistory> findAllOfClassId(Long classId);
}
