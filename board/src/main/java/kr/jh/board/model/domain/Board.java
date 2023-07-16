package kr.jh.board.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class Board extends TimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10 , nullable = false)
	private String writer;
	
	@Column(length = 100 , nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT" , nullable = false)
	private String content;
	
	@Builder
	public Board(Long id , String title , String content , String writer) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
}
