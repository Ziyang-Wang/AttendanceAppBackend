package com.didispace.att.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * response to front end
 */
@Data
@NoArgsConstructor
public class ClassTableVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private LocalDateTime createTime;

  private Long teId;

}
