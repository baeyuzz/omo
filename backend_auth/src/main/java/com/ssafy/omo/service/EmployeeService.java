package com.ssafy.omo.service;

import com.ssafy.omo.model.Employee;
import com.ssafy.omo.payload.ApiResponse;
import com.ssafy.omo.payload.EmployeeRequest;
import com.ssafy.omo.payload.EmployeeResponse;
import com.ssafy.omo.security.UserPrincipal;

import java.util.List;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	List<Employee> getEmployeesByCreatedBy(String username);

	Employee updateEmployee(Long id, EmployeeRequest newEmployeeRequest, UserPrincipal currentUser);

	ApiResponse deleteEmployee(Long id, UserPrincipal currentUser);

	EmployeeResponse addEmployee(EmployeeRequest employeeRequest, UserPrincipal currentUser);

	Employee getEmployee(Long id);

}
