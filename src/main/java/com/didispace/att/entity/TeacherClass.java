package com.didispace.att.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * teacher class mapping, one teacher can teach many class
 */
@Data
@NoArgsConstructor
public class TeacherClass implements Serializable {

  private static final long serialVersionUID = 2363686747006614262L;
  private Long id;
  private Long teId;
  private Long classId;

}
