package com.silmarfnascimento.bootcampproject.dto;

public record UserRegister(
    String name,
    String username,
    String email,
    String password,
    String image
) {

}
