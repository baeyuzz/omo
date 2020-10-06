package com.ssafy.omo.controller;

import com.ssafy.omo.model.Employee;
import com.ssafy.omo.model.user.User;
import com.ssafy.omo.payload.*;
import com.ssafy.omo.security.CurrentUser;
import com.ssafy.omo.security.UserPrincipal;
import com.ssafy.omo.service.EmployeeService;
import com.ssafy.omo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "User Rest API", description = "Defines endpoints for the logged in user. It's secured by default")
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "현재 로그인한 유저")
	public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = userService.getCurrentUser(currentUser);

		return new ResponseEntity< >(userSummary, HttpStatus.OK);
	}

	@ApiOperation(value = "사용가능한 유저이름")
	@GetMapping("/checkUsernameAvailability")
	public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "username") String username) {
		UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(username);

		return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
	}

	@ApiOperation(value = "사용가능한 이메일")
	@GetMapping("/checkEmailAvailability")
	public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
		UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email);
		return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
	}

	@ApiOperation(value = "유저 프로필")
	@GetMapping("/{username}/profile")
	public ResponseEntity<UserProfile> getUSerProfile(@PathVariable(value = "username") String username) {
		UserProfile userProfile = userService.getUserProfile(username);

		return new ResponseEntity< >(userProfile, HttpStatus.OK);
	}

	@ApiOperation(value = "멤버들 보기")
	@GetMapping("/{username}/Employees")
	public ResponseEntity<List<Employee>> getEmployeesCreatedBy(@PathVariable(value = "username") String username) {
		List<Employee> response = employeeService.getEmployeesByCreatedBy(username);

		return new ResponseEntity<  >(response, HttpStatus.OK);
	}

	@ApiOperation(value = "유저 추가")
	@PostMapping
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {

		User newUser = userService.addUser(user);

		return new ResponseEntity< >(newUser, HttpStatus.CREATED);
	}

	@ApiOperation(value = "유저 업데이트")
	@PutMapping("/{username}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User newUser,
			@PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser) {
		User updatedUSer = userService.updateUser(newUser, username, currentUser);

		return new ResponseEntity< >(updatedUSer, HttpStatus.CREATED);
	}

	@ApiOperation(value = "삭제")
	@DeleteMapping("/{username}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username,
			@CurrentUser UserPrincipal currentUser) {
		ApiResponse apiResponse = userService.deleteUser(username, currentUser);

		return new ResponseEntity< >(apiResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "정보수정")
	@PutMapping("/setOrUpdateInfo")
	public ResponseEntity<UserProfile> setAddress(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody InfoRequest infoRequest) {
		UserProfile userProfile = userService.setOrUpdateInfo(currentUser, infoRequest);

		return new ResponseEntity< >(userProfile, HttpStatus.OK);
	}

	@ApiOperation(value = "멤버 추가")
	@PostMapping("/emp")
	public ResponseEntity<EmployeeResponse> registerEmp(@Valid @RequestBody EmployeeRequest employeeRequest) {

		return new ResponseEntity< >(employeeService.addEmployee(employeeRequest), HttpStatus.CREATED);
	}

	@ApiOperation(value = "멤버 업데이트")
	@PutMapping("/emp/{empname}")
	public ResponseEntity<Employee> updateEmp(@Valid @RequestBody EmployeeRequest employeeRequest,
										   @PathVariable(value = "empname") String phone) {
		Employee employee = employeeService.updateEmployee(phone,employeeRequest);

		return new ResponseEntity< >(employee, HttpStatus.CREATED);
	}

	@ApiOperation(value = "멤버 삭제")
	@DeleteMapping("/emp/{empname}")
	public ResponseEntity<ApiResponse> deleteEmp(@PathVariable(value = "username") String name,
												 @PathVariable(value = "username") String phone) {
		ApiResponse apiResponse = employeeService.deleteEmployee(name,phone);

		return new ResponseEntity< >(apiResponse, HttpStatus.OK);
	}

}
