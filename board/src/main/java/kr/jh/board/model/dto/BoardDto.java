package kr.jh.board.model.dto;

import java.time.LocalDateTime;

import kr.jh.board.model.domain.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
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
	public BoardDto(Long id , String writer , String title , String content , LocalDateTime creaDateTime , LocalDateTime updateDateTime) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createDate = creaDateTime;
		this.updateDate = updateDateTime;
	}
	
	
}
