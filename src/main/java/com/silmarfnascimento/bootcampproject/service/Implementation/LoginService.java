package com.silmarfnascimento.bootcampproject.service.Implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.silmarfnascimento.bootcampproject.dto.Login;
import com.silmarfnascimento.bootcampproject.dto.Session;
import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import com.silmarfnascimento.bootcampproject.security.JWTCreator;
import com.silmarfnascimento.bootcampproject.security.JWTObject;
import com.silmarfnascimento.bootcampproject.security.SecurityConfiguration;
import com.silmarfnascimento.bootcampproject.service.ILoginService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Service
public class LoginService implements ILoginService {
  @Autowired
  private SecurityConfiguration securityConfig;

  @Autowired
  private IUserRepository userRepository;

  public ServiceResponse login(Login login){
    User userFound = userRepository.findByUsername(login.username());
    if(userFound != null) {
      var passwordVerify = BCrypt.verifyer().verify(login.password().toCharArray(), userFound.getPassword());
      if (!passwordVerify.verified) {
        return new ServiceResponse("UNAUTHORIZED", "Senha ou login inválidos");
      }

      JWTObject jwtObject = new JWTObject();
      jwtObject.setUsername(userFound.getUsername());
      jwtObject.setPassword(userFound.getPassword());
      jwtObject.setCreatedAt(new Date(System.currentTimeMillis()));
      jwtObject.setExpiresAt((new Date(System.currentTimeMillis() + SecurityConfiguration.EXPIRATION)));

      Session session = new Session(userFound.getUsername(), JWTCreator.create(SecurityConfiguration.PREFIX, SecurityConfiguration.KEY, jwtObject));

      return new ServiceResponse("OK", session);
    }else {
      throw new RuntimeException("Erro ao tentar fazer login");
    }
  }
}
