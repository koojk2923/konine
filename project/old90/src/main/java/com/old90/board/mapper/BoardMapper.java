package com.old90.board.mapper;

import java.util.List;

import com.old90.board.vo.BoardVO;
import com.webjjang.util.PageObject;

//  interface 작성 -> interface 상속 받은 클래스 작성 -> 클래스 생성 interface 타입으로 저장해서 실행
public interface BoardMapper {
	
	// dao에서 작성한 메서드 형식으로 만들어준다.
	// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
	// 주고 받는 데이터 타입 정의, sql 문과 처리 명령문이 필요한다.-> BoardMapper.xml, 
	
	// 1-1. 리스트
	public List<BoardVO> list(PageObject pageObject) throws Exception;
	// 1-2. 전체 데이터 개수
	public long getTotalRow(PageObject pageObject) throws Exception;
	// 2-1. 보기
	public BoardVO view(long no) throws Exception;
	// 2-2. 조회수 1증가
	public int increase(long no) throws Exception;
	// 3. 글쓰기
	public int write(BoardVO vo) throws Exception;
	// 4. 수정
	public int update(BoardVO vo) throws Exception;
	// 5. 삭제
	public int delete(long no) throws Exception;

}
