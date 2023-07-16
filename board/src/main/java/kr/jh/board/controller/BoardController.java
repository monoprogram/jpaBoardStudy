package kr.jh.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.jh.board.model.domain.Board;
import kr.jh.board.model.dto.BoardDto;
import kr.jh.board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public ModelAndView boardList(@RequestParam(value="page" , defaultValue = "1") int pNm) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDto> bList =  boardService.getBoardList(pNm);
		
		mav.addObject("blist", bList);
		
		return mav;
	}
	
	@GetMapping("/post")
	public String write() {
		return "board/post";
	}
	
	@PostMapping("/post")
	public String write(BoardDto boardDto) throws Exception {
		try {
			Long result = boardService.savePost(boardDto);
			
			if(result < 0 ) {
				throw new Exception("board write Error");
			} 
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/post/{bno}")
	public ModelAndView details(@PathVariable("bno") Long bno) throws Exception {
		ModelAndView  mav = new ModelAndView();
		Optional<Board> bto = boardService.getBoardContent(bno);
		
		mav.addObject("bDetail", bto);
		mav.setViewName("board/detail");
		return mav;
	}
	
	@GetMapping("/post/edit/{bno}")
	public ModelAndView edit(@PathVariable("bno") Long bno) throws Exception {
		ModelAndView mav = new ModelAndView();
		Optional<Board> bDto = boardService.getBoardContent(bno);
		
		mav.addObject("bContent", bDto);
		mav.setViewName("board/update");
		return mav;
	}
	
	@PutMapping("/post/edit/{bno}")
	public String update(BoardDto boardDto) {
		boardService.updateBoardContent(boardDto);
		return "redirect:/board/list";
	}
	
	@DeleteMapping("/post/{bno}")
	public String delete(@PathVariable("bno") Long bno) throws Exception {
		boardService.deleteWrite(bno);
		
		return "redirect:/board/list";
	}
	
}
