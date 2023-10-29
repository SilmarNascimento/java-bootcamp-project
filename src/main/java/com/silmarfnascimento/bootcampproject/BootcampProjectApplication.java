package com.silmarfnascimento.bootcampproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "BlogPostApi", version = "1", description = "API desenvolvida para um Blog"))
public class BootcampProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootcampProjectApplication.class, args);
  }

}
