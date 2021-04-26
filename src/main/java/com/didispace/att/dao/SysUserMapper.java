package com.didispace.att.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.att.entity.SysUser;

@Mapper
public interface SysUserMapper {

  @Select("select * from sys_user")
  List<SysUser> findAll();

  @Select("select * from sys_user WHERE username = #{username}")
  SysUser findByName(@Param("username") String username);

  @Select("select * from sys_user WHERE id = #{id}")
  SysUser findById(@Param("id") Long id);
  
  @Select("<script>" +
          "select * from sys_user WHERE id in  " +
          "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
              "#{item}" +
          "</foreach>" +
      "</script>")
  List<SysUser> findByIds(@Param("ids") List<Long> ids);

  @Insert("insert into sys_user (username,password,name,status,role,email,pid,create_time) values (#{username},#{password},#{nickname},#{status},#{role},#{email},#{pid},#{createTime})")
  int save(@Param("username") String username, @Param("password") String password,
      @Param("name") String nickname, @Param("status") int status, @Param("role") int role,
      @Param("email") String email, @Param("pid") String pid,
      @Param("createTime") LocalDateTime createTime);


  @Delete("delete from sys_user where id = #{id}")
  int delById(@Param("id") Long id);
}
