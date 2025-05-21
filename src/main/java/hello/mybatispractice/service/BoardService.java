package hello.mybatispractice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.mybatispractice.boad.dto.BoardDTO;
import hello.mybatispractice.boad.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public void save(BoardDTO boardDTO) {
		boardRepository.save(boardDTO);
	}
}
