package com.ssafy.omo.repository;

import com.ssafy.omo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByCreatedBy(Long userId);
	Long countByCreatedBy(Long userId);
	Optional<Employee> findByPhoneAndName(String name, String phone);
}
