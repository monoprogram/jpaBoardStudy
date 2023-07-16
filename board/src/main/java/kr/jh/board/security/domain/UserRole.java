package kr.jh.board.security.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "roles")
@ToString
@NoArgsConstructor
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "role_name" , nullable = false )
	private String roleName;
	
	@ManyToMany(mappedBy = "roles" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<User> users;
	
	
	public UserRole(String roleName) { this.roleName = roleName; }
}
