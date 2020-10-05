package com.ssafy.omo.model.role;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import static com.ssafy.omo.model.role.RoleName.ROLE_ADMIN;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "name")
	private RoleName name = ROLE_ADMIN;

	public Role(RoleName name) {
		this.name = name;
	}
}
