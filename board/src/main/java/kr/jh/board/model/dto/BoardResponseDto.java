package kr.jh.board.model.dto;

import java.time.LocalDateTime;

import kr.jh.board.model.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardResponseDto {

	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@Builder
	public BoardResponseDto(Board bEntity) {
		this.id = bEntity.getId();
		this.writer = bEntity.getWriter();
		this.title = bEntity.getTitle();
		this.content = bEntity.getContent();
		this.createDate = bEntity.getCreateDateTime();
		this.updateDate = bEntity.getUpdateDateTime();
	}
}
