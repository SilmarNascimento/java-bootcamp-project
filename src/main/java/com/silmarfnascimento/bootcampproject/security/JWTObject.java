package com.silmarfnascimento.bootcampproject.security;

import com.silmarfnascimento.bootcampproject.utils.SecurityConfiguration;
import lombok.Data;

import java.util.Date;

@Data
public class JWTObject {

  private String username;
  private Date createdAt;
  private Date expiresAt;

  public JWTObject(String username) {
    this.username = username;
    this.createdAt = new Date(System.currentTimeMillis());
    this.expiresAt = new Date(System.currentTimeMillis() + SecurityConfiguration.EXPIRATION);
  }
}
