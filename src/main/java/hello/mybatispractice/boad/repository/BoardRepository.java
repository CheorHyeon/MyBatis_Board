package hello.mybatispractice.boad.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import hello.mybatispractice.boad.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
	// Mapper를 호출하는 방법 중 하나
	private final SqlSession sqlSession;

	public void save(BoardDTO boardDTO) {
		// Board : Mapper의 name space를 가리킴, save : query문을 담고있는 태그
		// 파라미터 넘길 데이터, 파라미터 1개만 적을 수 있음 (2개 이상이라면 HashMap 등)
		sqlSession.insert("Board.save", boardDTO);
	}
}
