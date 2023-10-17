package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.dto.Login;

public interface ILoginService {
  ServiceResponse login(Login login);
}
