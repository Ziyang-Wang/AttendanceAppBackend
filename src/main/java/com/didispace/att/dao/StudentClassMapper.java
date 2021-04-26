package com.didispace.att.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.att.entity.StudentClass;

@Mapper
public interface StudentClassMapper {

  @Select("select * from student_class")
  List<StudentClass> findAll();

  @Select("select * from student_class WHERE st_id = #{stId}")
  StudentClass findById(@Param("stId") Long stId);
  
  @Select("select * from student_class WHERE class_id = #{classId}")
  List<StudentClass> findByClassId(@Param("classId") Long classId);

  @Select("select count(*) from student_class WHERE class_id = #{classId}")
  int countByClassId(@Param("classId") Long classId);
  

  @Select("<script>" +
          "select count(*) from student_class WHERE class_id in " +
          "<foreach item='item' index='index' collection='classIds' open='(' separator=',' close=')'>" +
              "#{item}" +
          "</foreach>" +
      "</script>")
  int countByClassIds(@Param("classIds") List<Long> classIds);


  @Delete("delete from student_class where st_id = #{stId}")
  int delById(@Param("stId") Long stId);
  
  

}
