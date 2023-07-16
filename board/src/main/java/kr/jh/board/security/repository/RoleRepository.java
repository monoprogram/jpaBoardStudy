package kr.jh.board.security.repository;

import org.springframework.data.repository.CrudRepository;

import kr.jh.board.security.domain.UserRole;

public interface RoleRepository extends CrudRepository<UserRole, Long>{

}
