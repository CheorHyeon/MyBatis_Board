package hello.mybatispractice.boad.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hello.mybatispractice.boad.dto.BoardDTO;
import hello.mybatispractice.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/save")
	@Operation(summary = "게시글 작성 API", description = "게시글을 작성합니다.")
	public String save(@RequestBody BoardDTO boardDTO) {
		System.out.println("boardDTO = " + boardDTO);
		boardService.save(boardDTO);
		return "success";
	}
}
