package com.silmarfnascimento.bootcampproject.utils;

import org.springframework.http.HttpStatus;

public class mapHTTPStatus {
  public static HttpStatus mapHttpStatus(String status) {
    return switch (status) {
      case "OK" -> HttpStatus.OK;
      case "CREATED" -> HttpStatus.CREATED;
      case "NO_CONTENT" -> HttpStatus.NO_CONTENT;
      case "BAD_REQUEST" -> HttpStatus.BAD_REQUEST;
      case "UNAUTHORIZED" -> HttpStatus.UNAUTHORIZED;
      case "FORBIDDEN" -> HttpStatus.FORBIDDEN;
      case "NOT_FOUND" -> HttpStatus.NOT_FOUND;
      default -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }
}
