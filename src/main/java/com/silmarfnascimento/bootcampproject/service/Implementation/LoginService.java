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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService implements ILoginService {
  @Autowired
  private IUserRepository userRepository;

  public ServiceResponse login(Login login){
    Optional<User> userFound = userRepository.findByUsername(login.username());
    if(userFound.isPresent()) {
      User user = userFound.get();
      var passwordVerify = BCrypt.verifyer().verify(login.password().toCharArray(), user.getPassword());
      if (!passwordVerify.verified) {
        return new ServiceResponse("UNAUTHORIZED", "Invalid login or password");
      }

      JWTObject jwtObject = new JWTObject();
      jwtObject.setUsername(user.getUsername());
      jwtObject.setPassword(user.getPassword());
      jwtObject.setCreatedAt(new Date(System.currentTimeMillis()));
      jwtObject.setExpiresAt((new Date(System.currentTimeMillis() + SecurityConfiguration.EXPIRATION)));

      Session session = new Session(user.getUsername(), JWTCreator.create(SecurityConfiguration.PREFIX, SecurityConfiguration.KEY, jwtObject));

      return new ServiceResponse("OK", session);
    }else {
      throw new RuntimeException("Erro ao tentar fazer login");
    }
  }
}
