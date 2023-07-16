package kr.jh.board.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Table(name = "user")
@Entity
@Data
@ToString
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1456564932796196666L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "user_id" , nullable = false , unique = true  , length = 50)
	private String userId;
	
	@Column(name = "user_pw" , nullable = false , length = 200)
	private String userPw;
	
	@Column(name = "user_email" , nullable = false , unique = true , length = 50)
	private String email;
	
	@Column(name = "reg_date")
	@CreationTimestamp
	private Date regDate;
	
	@Column(name = "modify_date" )
	@UpdateTimestamp
	private Date modifyDate;
	
	@Column(name = "enabled" , nullable = false)
	private boolean enabled;
	
	// 다 - 대 - 다 조인으로 권한 리스트 가져오기
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns = {@JoinColumn(name = "USER_ID" , referencedColumnName = "ID")  }
	,inverseJoinColumns = {@JoinColumn(name="ROLE_ID" , referencedColumnName = "ID")})
	private List<UserRole> roles;
	
	
	// role 리스트 정보를 담음
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPw;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
