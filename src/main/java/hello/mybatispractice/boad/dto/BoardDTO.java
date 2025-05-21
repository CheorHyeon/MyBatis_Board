package hello.mybatispractice.boad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BoardDTO(
	@Schema(description = "게시글 Pk", example = "3")
	Long id,
	@Schema(description = "게시글 작성자명", example = "cheor")
	String boardWriter,
	@Schema(description = "게시글 비밀번호", example = "1234")
	String boardPass,
	@Schema(description = "게시글 제목", example = "게시글 제목입니다.")
	String boardTitle,
	@Schema(description = "게시글 내용", example = "게시글 내용입니다.")
	String boardContents,
	@Schema(description = "게시글 히트수", example = "3")
	int boardHits,
	@Schema(description = "게시글 작성일", example = "2025-05-13")
	String createdAt
) {
}