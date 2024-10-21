//package com.example.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.demo.entities.Login;
//import com.example.demo.repositories.LoginRepository;
//
//
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private LoginRepository loginRepository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       
//       Login login = loginRepository.getLoginByUser(username)
//    		   .orElseThrow(() -> 
//				new UsernameNotFoundException("Username not found !!!!!"));
//    		         
//		
//		return new CustomUserDetails(login);
//	}
//
//}
