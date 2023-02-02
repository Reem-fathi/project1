package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
  private String email;
  private  String name;
  private Integer phone;
  private String password;
//private Integer user_id;
//  private Integer roleId;
//  private String userName;
//
//  private String password;
//  private Integer empId;

}
