package com.didispace.att.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class info
 */
@Data
@NoArgsConstructor
public class ClassTable implements Serializable {


  private static final long serialVersionUID = -5011258267129627767L;

  private Long id;
  private String name;
  private LocalDateTime createTime;

}
