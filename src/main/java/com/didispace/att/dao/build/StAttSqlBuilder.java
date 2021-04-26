package com.didispace.att.dao.build;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class StAttSqlBuilder {

  public String buildConditionForStAttSql(@Param("stId") Long stId, @Param("classId") Long classId,
      @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime) {
    return new SQL() {
      {
        SELECT(" * ");
        FROM(
            " 	(SELECT a.*,u.nickname,c.name classname FROM st_att a LEFT JOIN sys_user u ON a.st_id = u.id LEFT JOIN  class_table c ON a.class_id = c.id)  r1");
        if (stId != null) {
          WHERE("r1.st_id = " + stId);
        }
        if (classId != null) {
          WHERE("r1.class_id = " + classId);
        }
        if (startTime != null) {
          WHERE("r1.create_time > '" + startTime + "'");
        }
        if (endTime != null) {
          WHERE("r1.create_time < '" + endTime + "'");
        }
        ORDER_BY(" r1.id desc");
      }
    }.toString();
  }
}
