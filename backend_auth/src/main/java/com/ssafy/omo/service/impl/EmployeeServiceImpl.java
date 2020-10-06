package com.ssafy.omo.service.impl;

import com.ssafy.omo.exception.ResourceNotFoundException;
import com.ssafy.omo.exception.UnauthorizedException;
import com.ssafy.omo.model.Employee;
import com.ssafy.omo.model.role.RoleName;
import com.ssafy.omo.model.user.User;
import com.ssafy.omo.payload.ApiResponse;
import com.ssafy.omo.payload.EmployeeRequest;
import com.ssafy.omo.payload.EmployeeResponse;
import com.ssafy.omo.repository.EmployeeRepository;
import com.ssafy.omo.repository.UserRepository;
import com.ssafy.omo.security.UserPrincipal;
import com.ssafy.omo.service.EmployeeService;
import com.ssafy.omo.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> employees = employeeRepository.findAll();

		return employees;
	}

	@Override
	public List<Employee> getEmployeesByCreatedBy(String username) {
		User user = userRepository.getUserByName(username);
		List<Employee> employees = employeeRepository.findByCreatedBy(user.getId());

		return employees;
	}




	@Override
	public Employee updateEmployee(String phone, EmployeeRequest newEmployeeRequest) {
		Employee employee = employeeRepository.findByPhone(phone);

			employee.setName(newEmployeeRequest.getName());
			employee.setPhone(newEmployeeRequest.getPhone());
			employee.setAddress(newEmployeeRequest.getAddress());
			return employeeRepository.save(employee);
	}

	@Override
	public ApiResponse deleteEmployee(String name, String phone) {
		Employee employee = employeeRepository.findByPhoneAndName(name,phone);
			employeeRepository.deleteByPhone(phone);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted");

	}

	@Override
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {

		Employee employee = new Employee();
		employee.setAddress(employeeRequest.getAddress());
		employee.setPhone(employeeRequest.getPhone());
		employee.setName(employeeRequest.getName());
		employee.setUser(userRepository.findByUsername(employeeRequest.getUserId()).orElseThrow(null));

		Employee newEmployee = employeeRepository.save(employee);

		EmployeeResponse employeeResponse = new EmployeeResponse();

		employeeResponse.setName(newEmployee.getName());
		employeeResponse.setPhone(newEmployee.getPhone());

		return employeeResponse;
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.ID, id));
	}

}
