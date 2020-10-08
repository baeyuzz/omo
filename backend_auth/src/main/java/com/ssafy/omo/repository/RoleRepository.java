package com.ssafy.omo.repository;

import com.ssafy.omo.model.role.Role;
import com.ssafy.omo.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName name);
}
