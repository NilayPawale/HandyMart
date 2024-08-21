//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//@Bean
//public CustomUserDetailsService getCustomUserDetailsService() {
//	return new CustomUserDetailsService();
//}
//
//@Bean
//public BCryptPasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//}
//@Bean
//public DaoAuthenticationProvider authenticationProvider() {
//	DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//	authenticationProvider.setUserDetailsService(this.getCustomUserDetailsService());
//	authenticationProvider.setPasswordEncoder(passwordEncoder());
//	return authenticationProvider;
//}
//
// 
//
//}
