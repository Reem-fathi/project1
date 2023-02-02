package com.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_list",uniqueConstraints =
  {
    @UniqueConstraint(columnNames = {"emp_id"})})

public class Employee {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "emp_id")
  private Integer empId;
  private String name;
  private String email;
  private String project;
  private String address;
  private String skill;
  private String dob;
  private String joiningDate;
  private float salary;
  @OneToOne
  @JoinColumn(name = "project_id")
  private Project pro;
  private String modified="Not edited";

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

}
