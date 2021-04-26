package com.didispace.att.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student class mapping, one student belongs to one class
 */
@Data
@NoArgsConstructor
public class StudentClass implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -9032342553624486849L;
  private Long stId;
  private Long classId;

}
