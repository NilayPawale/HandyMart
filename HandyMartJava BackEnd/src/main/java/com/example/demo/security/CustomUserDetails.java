//package com.example.demo.security;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.demo.entities.Login;
//
//public class CustomUserDetails implements UserDetails {
//     private Login login ;
//     
//     
//     
//	
//
//	public CustomUserDetails(Login login) {
//		super();
//		this.login = login;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		SimpleGrantedAuthority   simpleGrantedAuthority  =	new SimpleGrantedAuthority(login.getRole_id().getRole_name());
//		return List.of(simpleGrantedAuthority);
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return login.getUsername();
//	}
//
//	@Override
//	public String getUsername() {
//		
//		return login.getPassword_hash();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
