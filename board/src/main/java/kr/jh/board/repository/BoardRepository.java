package kr.jh.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jh.board.model.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	// 
	/**
	 * @Param query
	 * @Transactional
	 * @Modifying
	 * @Query(value = query , nativeQuery = true)
	 * public int updateBoard(@Param("boardDto") BoardDto boardDto);
	 */
	
}
