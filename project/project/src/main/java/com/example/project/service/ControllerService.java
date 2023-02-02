package com.example.project.service;

import com.example.project.dto.JwtRequest;
import com.example.project.dto.JwtResponse;
import com.example.project.dto.RegisterDto;

import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;

@Service
public class ControllerService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CustomUserDetailService customUserDetailService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private RoleRepository repository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  final Logger logger= LoggerFactory.getLogger(ControllerService.class);
  public ResponseEntity<String> userRegister(RegisterDto registerdto){
    if(userRepository.existsByemail(registerdto.getEmail())){
      logger.error("email is already taken");
      return new ResponseEntity<>("email is already taken", HttpStatus.BAD_REQUEST);
    }
    User user=new User();
    user.setEmail(registerdto.getEmail());
    user.setName(registerdto.getName());
    user.setPhone(registerdto.getPhone());
    user.setPassword(passwordEncoder.encode(registerdto.getPassword()));
    Role role =  repository.findByName("ROLE_USER").orElseThrow(()->new UsernameNotFoundException("role exception"));
   user.setRole((com.example.project.model.Role) role);

    userRepository.save(user);
    logger.info("user registered successfully");
    return new ResponseEntity<>("User is registered successfully",HttpStatus.OK);
  }
  public ResponseEntity<JwtResponse> loginUser(JwtRequest jwtRequest){
    try{
      this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));
      logger.info("Login Successful");
    }catch(AuthenticationException e){
      logger.error("Bad credentials");
      return  new ResponseEntity<>(new JwtResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
    }
    String token=this.jwtService.generateToken(jwtRequest.getEmail());
    return ResponseEntity.ok(new JwtResponse(token));
  }
}



