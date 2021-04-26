package com.didispace.att.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.att.entity.TeacherClass;

@Mapper
public interface TeacherClassMapper {

  @Select("select * from teacher_class")
  List<TeacherClass> findAll();


  @Select("select * from teacher_class WHERE id = #{id}")
  TeacherClass findById(@Param("id") Long id);


  @Delete("delete from teacher_class where id = #{id}")
  int delById(@Param("id") Long id);
}
