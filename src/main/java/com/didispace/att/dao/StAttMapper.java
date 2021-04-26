package com.didispace.att.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.didispace.att.dao.build.StAttSqlBuilder;
import com.didispace.att.entity.StAtt;
import com.didispace.att.vo.StAttVo;

@Mapper
public interface StAttMapper {

  @SelectProvider(type = StAttSqlBuilder.class, method = "buildConditionForStAttSql")
  List<StAttVo> buildConditionForStAttSql(@Param("stId") Long stId, @Param("classId") Long classId,
      @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

  @Select("select * from st_att WHERE class_id = #{classId} and create_time > #{createTime}")
  List<StAtt> findByClassId(@Param("classId") Long classId,
      @Param("createTime") LocalDateTime createTime);


  @Insert("insert into st_att (st_id,class_id,att_mark,create_time) values (#{stId},#{classId},#{attMark},#{createTime})")
  int save(@Param("stId") Long stId, @Param("classId") Long classId,
      @Param("attMark") int attMark, @Param("createTime") LocalDateTime createTime);


  @Select("select count(*) from st_att WHERE st_id = #{stId} and create_time > #{createTime}")
  int countByStIdOfTime(@Param("stId") Long stId, @Param("createTime") LocalDateTime createTime);


  @Select("select count(*) from st_att WHERE class_id = #{classId} and create_time > #{createTime}")
  int countByClassIdOfTime(@Param("classId") Long classId,
      @Param("createTime") LocalDateTime createTime);


  @Select("<script>" +
      "select count(*) from st_att WHERE create_time > #{createTime} and class_id in  " +
      "<foreach item='item' index='index' collection='classIds' open='(' separator=',' close=')'>" +
      "#{item}" +
      "</foreach>" +
      "</script>")
  int countByClassIdsOfTime(@Param("classIds") List<Long> classIds,
      @Param("createTime") LocalDateTime createTime);

}
