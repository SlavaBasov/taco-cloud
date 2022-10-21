package com.example.server.config;

import com.example.model.model.Usr;
import com.example.model.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

  @Bean
  public ApplicationRunner dataLoader(
      UserRepository repo, PasswordEncoder encoder) {
    return args -> {
      repo.save(
          new Usr("habuma", encoder.encode("password"), "ROLE_ADMIN", "str",
              "city", "state", "rq", "123"));
      repo.save(
          new Usr("tacochef", encoder.encode("password"), "ROLE_ADMIN", "str",
              "city", "state", "rq", "123"));
    };
  }
}
