package com.didispace.att.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class history attendance for future reference
 */
@Data
@NoArgsConstructor
public class ClassAttHistory implements Serializable {

  private static final long serialVersionUID = 5679295039446869654L;
  private Long id;
  private Long classId;
  private Integer totalMarks;
  private Integer totalStudents;
  private Integer attRate;

  private LocalDateTime createTime;

}
