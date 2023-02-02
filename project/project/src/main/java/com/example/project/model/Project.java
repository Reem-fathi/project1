package com.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "project",uniqueConstraints = {
  @UniqueConstraint(columnNames = {"projectName"})})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Project {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)

  private Integer empId;
  private String projectName;
  private Integer projectId;
  private Integer startDate;
  private Integer endDate;
  private String technology;
  @OneToMany(mappedBy = "pro")
  private List<Employee> employee;
}
