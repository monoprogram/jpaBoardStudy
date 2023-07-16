package kr.jh.board.security.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "persistent_logins")
public class PersistentLogins {

	@Id
	@Column(name = "series")
	private String series;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "last_user")
	private Date lastUsed;
	
}
