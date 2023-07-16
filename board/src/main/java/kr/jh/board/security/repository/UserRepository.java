package kr.jh.board.security.repository;

import org.springframework.data.repository.CrudRepository;

import kr.jh.board.security.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(String userid);
}
