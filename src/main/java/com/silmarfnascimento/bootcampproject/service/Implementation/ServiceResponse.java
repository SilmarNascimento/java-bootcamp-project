package com.silmarfnascimento.bootcampproject.service.Implementation;

import lombok.Data;

@Data
public class ServiceResponse {
  private String status;
  private Object data;

  public ServiceResponse(String status, Object data) {
    this.status = status;
    this.data = data;
  }
}
