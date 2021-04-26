package com.didispace.att.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * system user info
 */
@Data
@NoArgsConstructor
public class SysUser implements Serializable {

  private static final long serialVersionUID = -1067143867483358561L;

  private Long id;

  private String username;
  private String password;
  private String name;
  private Integer status;
  private String email;
  private String pid;
  private Integer role;
  private LocalDateTime createTime;

}
