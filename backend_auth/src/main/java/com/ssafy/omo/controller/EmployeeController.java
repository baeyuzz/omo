package com.ssafy.omo.controller;


import com.ssafy.omo.model.Employee;
import com.ssafy.omo.payload.ApiResponse;
import com.ssafy.omo.payload.EmployeeRequest;
import com.ssafy.omo.payload.EmployeeResponse;
import com.ssafy.omo.security.CurrentUser;
import com.ssafy.omo.security.UserPrincipal;
import com.ssafy.omo.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Employee Rest API")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employee")
public class EmployeeController {
        @Autowired
        private EmployeeService employeeService;

        @GetMapping
        public ResponseEntity<List<Employee>> getAllEmployees( @CurrentUser UserPrincipal currentUser) {
            List<Employee> response = employeeService.getAllEmployees(currentUser);

            return new ResponseEntity<  >(response, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                            @CurrentUser UserPrincipal currentUser) {
            EmployeeResponse employeeResponse = employeeService.addEmployee(employeeRequest, currentUser);

            return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.CREATED);
        }

        @GetMapping("/{userName}/{phone}")
        public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable(name = "userName") String userName,@PathVariable(name = "phone") String phone) {
            Employee employee = employeeService.getEmployeeByPhoneAndName(userName,phone);

            return new ResponseEntity(
                    new EmployeeResponse().builder()
                            .name(employee.getName())
                            .address(employee.getAddress())
                            .phone(employee.getPhone())
                            .build(), HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long id,
                                                       @Valid @RequestBody EmployeeRequest employeeRequest, @CurrentUser UserPrincipal currentUser) {
            Employee employee = employeeService.updateEmployee(id, employeeRequest, currentUser);

            return new ResponseEntity<>(employee, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser) {
            ApiResponse apiResponse = employeeService.deleteEmployee(id, currentUser);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
}
