package com.didispace.att.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student attendance info
 */
@Data
@NoArgsConstructor
public class StAtt implements Serializable {

  private static final long serialVersionUID = -2755553861842260019L;
  private Long id;
  private Long stId;

  private Long classId;
  private Integer attMark;

  private LocalDateTime createTime;

}
