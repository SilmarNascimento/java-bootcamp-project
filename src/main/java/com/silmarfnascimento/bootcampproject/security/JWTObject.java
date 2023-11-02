package com.silmarfnascimento.bootcampproject.security;

import com.silmarfnascimento.bootcampproject.utils.SecurityConfiguration;
import lombok.Data;

import java.util.Date;

@Data
public class JWTObject {

  private String username;
  private String password;
  private Date createdAt;
  private Date expiresAt;

  public JWTObject(String username, String password) {
    this.username = username;
    this.password = password;
    this.createdAt = new Date(System.currentTimeMillis());
    this.expiresAt = new Date(System.currentTimeMillis() + SecurityConfiguration.EXPIRATION);
  }
}
