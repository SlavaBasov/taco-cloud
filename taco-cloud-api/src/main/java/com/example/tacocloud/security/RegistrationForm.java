package com.example.tacocloud.security;

import com.example.model.model.Usr;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
  private String username;
  private String password;
  private String fullname;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String phone;
  public Usr toUser(PasswordEncoder passwordEncoder) {
    return new Usr(username, passwordEncoder.encode(password),
        fullname, street, city, state, zip, phone);
  }
}
