package com.didispace.att.vo;

import java.time.LocalDateTime;


import lombok.Data;

/**
 * response to front end
 */
@Data
public class StAttVo {

  private Long id;
  private Long stId;

  private Long classId;
  private Integer attMark;

  private String classname;
  private LocalDateTime createTime;
}
