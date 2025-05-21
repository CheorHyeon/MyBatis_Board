package hello.mybatispractice.boad.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/list")
	@Operation(summary = "게시글 목록 전체 조회", description = "게시글 목록 전체를 조회합니다.")
	public List<BoardDTO> findAll() {
		return boardService.findAll();
	}
}
