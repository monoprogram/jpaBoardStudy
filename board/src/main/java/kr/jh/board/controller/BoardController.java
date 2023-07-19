package kr.jh.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.jh.board.model.dto.BoardDto;
import kr.jh.board.model.dto.BoardRequestDto;
import kr.jh.board.model.dto.BoardResponseDto;
import kr.jh.board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public ModelAndView boardList(@RequestParam(value="page" , defaultValue = "1") int pNm) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardResponseDto> bList =  boardService.getBoardList(pNm);
		
		mav.addObject("blist", bList);
		
		return mav;
	}
	
	@GetMapping("/post")
	public String write() {
		return "board/post";
	}
	
	@PostMapping("/post")
	public String write(BoardRequestDto boardDto) throws Exception {
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
		BoardResponseDto bresDto = boardService.getBoardContent(bno);
		
		mav.addObject("bDetail", bresDto);
		mav.setViewName("board/detail");
		return mav;
	}
	
	@GetMapping("/post/edit/{bno}")
	public ModelAndView edit(@PathVariable("bno") Long bno) throws Exception {
		ModelAndView mav = new ModelAndView();
		BoardResponseDto bDto = boardService.getBoardContent(bno);
		
		mav.addObject("bContent", bDto);
		mav.setViewName("board/update");
		return mav;
	}
	
	@PutMapping("/post/edit/{bno}")
	public String update(@ModelAttribute("bReqDto") BoardRequestDto bReqDto) {
		System.out.println("여기" + bReqDto.toString());
		// all arg 설정 하고 에러 안남
		boardService.updateBoardContent(bReqDto);
		return "redirect:/board/list";
	}
	
	@DeleteMapping("/post/{bno}")
	public String delete(@PathVariable("bno") Long bno) throws Exception {
		boardService.deleteWrite(bno);
		
		return "redirect:/board/list";
	}
	
}
