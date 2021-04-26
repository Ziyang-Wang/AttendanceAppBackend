package com.didispace.att.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private T data;
  private Integer code;
  private String msg;

  public static <T> Result<T> succeed(String msg) {
    return succeedWith(null, CommonErrorCode.OPERATION_SUCCESS.value(), msg);
  }

  public static <T> Result<T> succeedData(T model) {
    return succeedWith(model, CommonErrorCode.OPERATION_SUCCESS.value(),
        CommonErrorCode.OPERATION_SUCCESS.getMessage());
  }

  public static <T> Result<T> succeed(T model, String msg) {
    return succeedWith(model, CommonErrorCode.OPERATION_SUCCESS.value(), msg);
  }

  public static <T> Result<T> succeed() {
    return succeedWith(null, CommonErrorCode.OPERATION_SUCCESS.value(),
        CommonErrorCode.OPERATION_SUCCESS.getMessage());
  }

  public static <T> Result<T> succeedWith(T data, Integer code, String msg) {
    return new Result<T>(data, code, msg);
  }

  public static <T> Result<T> failed() {
    return failedWith(null, CommonErrorCode.OPERATION_FAILED.value(),
        CommonErrorCode.OPERATION_FAILED.getMessage());
  }

  public static <T> Result<T> failed(CommonErrorCode commonErrorCode) {
    return failedWith(null, commonErrorCode.value(), commonErrorCode.getMessage());
  }

  public static <T> Result<T> failed(String msg) {
    return failedWith(null, CommonErrorCode.OPERATION_FAILED.value(), msg);
  }

  public static <T> Result<T> failed(T model, String msg) {
    return failedWith(model, CommonErrorCode.OPERATION_FAILED.value(), msg);
  }

  public static <T> Result<T> failedWith(T data, Integer code, String msg) {
    return new Result<T>(data, code, msg);
  }

}
