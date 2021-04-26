package com.didispace.att.common;

public enum CommonErrorCode {


  OPERATION_SUCCESS(0, "OPERATION_SUCCESS"),


  OPERATION_FAILED(1, "OPERATION_FAILED"),


  USER_PWD_ERROR(10000401, "Wrong username or password");


  private final int value;

  private final String message;

  CommonErrorCode(int value, String message) {
    this.value = value;
    this.message = message;
  }

  /**
   * Return the integer value of this status code.
   */
  public int value() {
    return this.value;
  }

  /**
   * Return the reason phrase of this status code.
   */
  public String getMessage() {
    return this.message;
  }
}
