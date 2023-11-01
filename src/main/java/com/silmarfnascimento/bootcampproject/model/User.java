package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.List;
import lombok.Data;

import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity(name = "tb_users")
public class User extends UserDetails {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  private String image;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
