package com.example.tacocloud.config;

import com.example.tacocloud.model.Usr;
import com.example.tacocloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final String DESIGN_PAGE_URI = "/design";

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      Usr usr = userRepository.findByUsername(username);
      if(usr != null) return usr;
      throw new UsernameNotFoundException("User '" + username + "' not found");
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
//        .csrf().disable()
        .authorizeRequests()
        .antMatchers(DESIGN_PAGE_URI, "/orders").hasRole("USER")
        .antMatchers("/", "/h2-console/**", "/**").permitAll()
//        .and().csrf().ignoringAntMatchers("/h2-console/**", DESIGN_PAGE_URI, "/orders")
        .and().headers().frameOptions().sameOrigin()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl(DESIGN_PAGE_URI)
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .build();
  }
}
