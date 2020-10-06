package com.ssafy.omo.service;

import com.ssafy.omo.model.Employee;
import com.ssafy.omo.model.user.User;
import com.ssafy.omo.payload.ApiResponse;
import com.ssafy.omo.payload.EmployeeRequest;
import com.ssafy.omo.payload.EmployeeResponse;
import com.ssafy.omo.security.UserPrincipal;

import java.util.List;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	List<Employee> getEmployeesByCreatedBy(String username);

	Employee updateEmployee(String phone, EmployeeRequest newEmployeeRequest);

	ApiResponse deleteEmployee(String name, String phone);

	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Employee getEmployee(Long id);

}
