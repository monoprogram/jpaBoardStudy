package kr.jh.board.model.dto;

import java.time.LocalDateTime;

import kr.jh.board.model.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardRequestDto {
	private Long id;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	// DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만듦
	public Board toEntity() {
		Board build = Board.builder()
				.id(id)
				.writer(writer)
				.title(title)
				.content(content)
				.build();
		return build;
	}
	
	@Builder
	public BoardRequestDto(Board bEntity) {
		this.id = bEntity.getId();
		this.writer = bEntity.getWriter();
		this.title = bEntity.getTitle();
		this.content = bEntity.getContent();
		this.createDate = bEntity.getCreateDateTime();
		this.updateDate = bEntity.getUpdateDateTime();
	}
}
