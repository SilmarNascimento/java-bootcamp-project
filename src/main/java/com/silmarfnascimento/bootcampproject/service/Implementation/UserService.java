package com.silmarfnascimento.bootcampproject.service.Implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import com.silmarfnascimento.bootcampproject.service.IUserService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import com.silmarfnascimento.bootcampproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
  @Autowired
  private IUserRepository userRepository;

  @Override
  public ServiceResponse findAll() {
    List<User> users = userRepository.findAll();
    return new ServiceResponse("OK", users);
  }

  @Override
  public ServiceResponse findById(UUID id) {
    Optional<User> user = userRepository.findById(id);
    if(user.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "User not Found");
    }
    return new ServiceResponse("OK", user);
  }

  @Override
  public ServiceResponse create(User user) {
    Optional<User> userFound = this.userRepository.findByUsername(user.getUsername());
    if (userFound.isPresent()) {
      return new ServiceResponse("BAD_REQUEST", "User already exists!");
    }
    hashUserPassword(user);
    User createdUser = userRepository.save(user);
    return new ServiceResponse("CREATED", createdUser);
  }

  @Override
  public ServiceResponse update(UUID id, User user) {
    Optional<User> userFound = userRepository.findById(id);
    if (userFound.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "User not Found");
    }

    var passwordVerify = BCrypt.verifyer().verify(user.getPassword().toCharArray(), userFound.get().getPassword());
    if(!passwordVerify.verified) {
      hashUserPassword(user);
    }

    Utils.copyNonNullProperties(user, userFound.get());
    User updatedUser = userRepository.save(userFound.get());
    return new ServiceResponse("OK", updatedUser);
  }

  @Override
  public void delete(UUID id) {
    userRepository.deleteById(id);
  }

  private static void hashUserPassword(User user) {
    String passwordHashed = BCrypt.withDefaults()
        .hashToString(12, user.getPassword().toCharArray());
    user.setPassword(passwordHashed);
  }
}
