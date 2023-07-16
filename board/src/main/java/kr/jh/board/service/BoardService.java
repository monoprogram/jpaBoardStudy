package kr.jh.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.jh.board.model.domain.Board;
import kr.jh.board.model.dto.BoardDto;
import kr.jh.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public Optional<Board> getBoardContent(Long bno) {
		return boardRepository.findById(bno);
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public List<BoardDto> findAll() {
		List<Board> boardList =  boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			// list에 담겨 있는 board entity에서 빌더 패턴으로 list boardDto에 담기
			BoardDto boardDto =  BoardDto.builder()
					.id(board.getId())
					.writer(board.getWriter())
					.title(board.getTitle())
					.content(board.getContent())
					.creaDateTime(board.getCreateDateTime())
					.build();
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}

	public List<BoardDto> getBoardList(int pNm) {
		List<Board> boardList =  boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			// list에 담겨 있는 board entity에서 빌더 패턴으로 list boardDto에 담기
			BoardDto boardDto =  BoardDto.builder()
					.id(board.getId())
					.writer(board.getWriter())
					.title(board.getTitle())
					.content(board.getContent())
					.creaDateTime(board.getCreateDateTime())
					.updateDateTime(board.getUpdateDateTime())
					.build();
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}

	public void updateBoardContent(BoardDto boardDto) {
		
	}

	public void deleteWrite(Long bno) {
		boardRepository.deleteById(bno);
	}

}
