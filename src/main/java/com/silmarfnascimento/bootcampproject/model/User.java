package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  private String password;
  private String image;

  public User(String name, String username, String email, String password, String image) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.image = image;
  }

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
