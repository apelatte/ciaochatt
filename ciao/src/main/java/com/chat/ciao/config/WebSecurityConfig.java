package com.chat.ciao.config;

import com.chat.ciao.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;
  @Autowired
  private AuthenticationProvider authProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers("/",
            "/home").hasRole("USER")
        .requestMatchers("/api/users/getAll",
          "/api/users/getById/*").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .sessionManagement(sessionManager ->
        sessionManager
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Inhabilita la política de creación de sesiones)
      )
      .authenticationProvider(authProvider)
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .logout((logout) -> logout.permitAll());

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager(){
    UserDetails user = User.withDefaultPasswordEncoder()
      .username("user")
      .password("password")
      .roles("USER")
      .build();

    UserDetails admin = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("password")
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

}
