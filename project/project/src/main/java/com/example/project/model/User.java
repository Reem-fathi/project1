package com.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user",uniqueConstraints = {
  @UniqueConstraint(columnNames = {"email"})})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Integer user_id;
//  private Integer roleId;
//  private String userName;
   private String name;
  private String password;
  private Integer empId;
    private String email;
    private Integer phone;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

}
