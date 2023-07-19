package kr.jh.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.jh.board.model.domain.Board;
import kr.jh.board.model.dto.BoardRequestDto;
import kr.jh.board.model.dto.BoardResponseDto;
import kr.jh.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public Long savePost(BoardRequestDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public BoardResponseDto getBoardContent(Long bno) {
		Optional<Board> board = boardRepository.findById(bno);
		BoardResponseDto bRes = new BoardResponseDto(board.get());
		return bRes;
	}
	
	@Transactional(transactionManager = "jpaTranscationManager")
	public List<BoardResponseDto> findAll() {
		List<Board> boardList =  boardRepository.findAll();
		List<BoardResponseDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			// list에 담겨 있는 board entity에서 빌더 패턴으로 list boardDto에 담기
			BoardResponseDto boardDto = new BoardResponseDto(board); 
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}

	public List<BoardResponseDto> getBoardList(int pNm) {
		List<Board> boardList =  boardRepository.findAll();
		List<BoardResponseDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			// list에 담겨 있는 board entity에서 빌더 패턴으로 list boardDto에 담기
			BoardResponseDto boardDto =  new BoardResponseDto(board);
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}

	@Transactional(transactionManager = "jpaTranscationManager")
	public void updateBoardContent(BoardRequestDto bReqDto) {
		boardRepository.save(bReqDto.toEntity()).getId();
	}

	public void deleteWrite(Long bno) {
		boardRepository.deleteById(bno);
	}

}
