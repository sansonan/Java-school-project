package com.example.school.security;

import com.example.school.jwt.FilterChainExceptionHandler;
import com.example.school.jwt.JwtLoginFilter;
import com.example.school.jwt.TokenVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import static com.example.school.security.PermissionEnum.*;
import static com.example.school.security.RoleEnum.ADMIN;


@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private FilterChainExceptionHandler filterChainExceptionHandler;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
                .addFilterBefore(filterChainExceptionHandler, JwtLoginFilter.class)
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/","index.html","css/**","js/**","/api/users/**","/api/payments/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/users/**").hasAuthority(USER_READ.getDescription())
//                .requestMatchers(HttpMethod.POST,"/api/course/**").hasAuthority()
//                .requestMatchers(HttpMethod.GET,"/api/registrations/**").hasAuthority(REGISTRATION_READ.getDescription())
                .requestMatchers("/api/users/**").hasAuthority(USER_WRITE.getDescription())
                .requestMatchers("/api/users/**").hasRole(ADMIN.name())
                .requestMatchers("/api/registrations/**").hasAuthority(REGISTRATION_WRITE.getDescription())
                .requestMatchers("/api/registrations/**").hasRole(ADMIN.name())
                .requestMatchers("/api/promotion/**").hasAuthority(PROMOTION_READ.getDescription())
                .requestMatchers("/api/promotion/**").hasAuthority(PROMOTION_WRITE.getDescription())
                .requestMatchers("/api/promotion/**").hasRole(ADMIN.name())
                .requestMatchers("/api/courses/**").hasAuthority(COURSE_READ.getDescription())
                .requestMatchers("/api/courses/**").hasAuthority(COURSE_WRITE.getDescription())
                .requestMatchers("/api/courses/**").hasRole(ADMIN.name())

                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll();
        return http.build();

    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
