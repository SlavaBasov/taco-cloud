package com.example.tacocloud.config;

import com.example.tacocloud.model.Usr;
import com.example.tacocloud.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

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
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/ingredients").hasRole("USER")
        .antMatchers("/design", "/orders").hasRole("USER")
        .antMatchers("/", "/**").permitAll()
        .anyRequest().authenticated().and()
        .csrf().ignoringAntMatchers("/h2-console/**", "/api/**")
        .and().headers().frameOptions().sameOrigin().and() //for displaying frames in h2-console
        .formLogin().loginPage("/login").defaultSuccessUrl("/design").and()
        .logout().logoutSuccessUrl("/").and()
        .httpBasic().and()
        .build();
  }
}
