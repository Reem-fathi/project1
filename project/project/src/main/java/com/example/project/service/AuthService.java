package com.example.project.service;

import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.project.repository.EmployeeRepository;

import java.util.List;

@Service
public class AuthService {
  final Logger logger = LoggerFactory.getLogger(AuthService.class);
  @Autowired
  private EmployeeRepository employeeRepository;

  public ResponseEntity<List<Employee>> getAllEmployee() {
    try {
      return ResponseEntity.ok(employeeRepository.findAll());

    } catch (Exception e) {
      return (ResponseEntity<List<Employee>>) ResponseEntity.badRequest();
    }
  }

  public ResponseEntity<String> addEmployee(Employee employee) {
    try {
      employeeRepository.save(employee);
      logger.info("new employee added");
      return new ResponseEntity<String>("[" + employee.getEmpId() + "]" + "Employee added successfully", HttpStatus.CREATED);
    } catch (Exception e) {

      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }

  public ResponseEntity<Employee> getEmployee(Integer empId) {
    logger.info("get employee with given id");
    return new ResponseEntity<Employee>(this.employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("emp", "id",empId)), HttpStatus.FOUND);

  }




  public ResponseEntity<String> updateEmployee(Integer id, Employee updateEmployee) {
    try {
      logger.info("Function updateEmployee() called with id :" + id.toString() + " Employee is :" + updateEmployee.toString());
      Employee employee = employeeRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Employee->"+updateEmployee.toString()+" with id:"+id+" not found"));
      employee.setName(updateEmployee.getName());
      employee.setAddress(updateEmployee.getAddress());
      employee.setEmail(updateEmployee.getEmail());
      employee.setSkill(updateEmployee.getSkill());

      employee.setModified(updateEmployee.getModified());
      employeeRepository.save(employee);
      return new ResponseEntity<String>("Employee details updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

  }
  public ResponseEntity<String> deleteEmployee(Integer empId) {
    try {
      Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("employee", "Employee Id", empId));
      employeeRepository.delete(employee);
      logger.info("employee deleted with given id");
      return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
  }
}
