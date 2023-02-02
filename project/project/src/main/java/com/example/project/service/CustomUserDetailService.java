package com.example.project.service;
import com.example.project.model.Role;
import com.example.project.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.project.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetailService implements UserDetailsService {
  private  final UserRepository userRepo;

  public CustomUserDetailService(UserRepository userRepository) {
    this.userRepo = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepo.findByemail(email).orElseThrow(() -> new UsernameNotFoundException("User not found" + email));
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthority(user.getRole()));
  }
  private Collection<? extends GrantedAuthority> mapRolesToAuthority(Role role) {
    return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
  }
}
