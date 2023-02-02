package com.example.project.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;
  private String name;
  @OneToMany(mappedBy = "role")
  private List<User> users;
}
