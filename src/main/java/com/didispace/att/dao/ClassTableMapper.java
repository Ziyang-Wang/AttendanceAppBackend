package com.didispace.att.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.att.entity.ClassTable;
import com.didispace.att.vo.ClassTableVo;

@Mapper
public interface ClassTableMapper {

  @Select("select * from class_table")
  List<ClassTable> findAll();

  @Select("select ct.*, #{teId} from class_table ct")
  List<ClassTableVo> findAllClass(@Param("teId") Long teId);


  @Select("select * from class_table WHERE id = #{id}")
  ClassTable findById(@Param("id") Long id);

  @Select("select ct.*,tc.te_id from teacher_class tc LEFT JOIN class_table ct on tc.class_id = ct.id where tc.te_id=#{teId}")
  List<ClassTableVo> findAllOfTeId(@Param("teId") Long teId);

  @Delete("delete from class_table where id = #{id}")
  int delById(@Param("id") Long id);
}
