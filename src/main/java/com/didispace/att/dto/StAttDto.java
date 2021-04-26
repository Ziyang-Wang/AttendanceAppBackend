package com.didispace.att.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student attendance info from request
 */
@Data
@NoArgsConstructor
public class StAttDto {


  private static final long serialVersionUID = -1L;

  private Long stId;

  private Long classId;

  private Long startTime;
  private Long endTime;

}
