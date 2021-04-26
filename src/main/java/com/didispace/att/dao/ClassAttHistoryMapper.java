package com.didispace.att.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.att.entity.ClassAttHistory;

@Mapper
public interface ClassAttHistoryMapper {

  @Select("select * from class_att_history")
  List<ClassAttHistory> findAll();

  @Select("select * from class_att_history WHERE class_id = #{classId}")
  List<ClassAttHistory> findAllOfClassId(@Param("classId") Long classId);

  @Select("select * from class_att_history WHERE id = #{id}")
  ClassAttHistory findById(@Param("id") Long id);


  @Delete("delete from class_att_history where id = #{id}")
  int delById(@Param("id") Long id);


  @Insert("insert into class_att_history (class_id,total_marks,total_students,att_rate,create_time) values (#{classId},#{totalMarks},#{totalStudents},#{attRate},#{createTime})")
  int save(@Param("classId") Long classId, @Param("totalMarks") int totalMarks,
      @Param("totalStudents") int totalStudents, @Param("attRate") int attRate,
      @Param("createTime") LocalDateTime createTime);

}
