package kr.jh.board.security.repository;

import org.springframework.data.repository.CrudRepository;

import kr.jh.board.security.domain.PersistentLogins;

public interface TokenRepository extends CrudRepository<PersistentLogins, String> {

	int deleteByUsername(String username);
	
	PersistentLogins findBySeries(String series);
	
}
