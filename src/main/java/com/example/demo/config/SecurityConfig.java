package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@ComponentScan
@EntityScan
@EnableWebSecurity
public class SecurityConfig {

    /*  @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                  .antMatchers("/api/auth/**").permitAll()
                  .antMatchers("/api/test/**").permitAll()
                  .antMatchers("/api/v1/**").permitAll()
                  .anyRequest().authenticated();

          return http.build();
      }**/
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails aida = User.builder()
                .username("aida")
                .password("password")
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(aida);
    }

}
