package com.old90.record.mapper;

import java.util.List;

import com.old90.record.vo.RecordVO;
import com.webjjang.util.PageObject;

/**
 * 
 * @packageName	: com.old90.record.mapper
 * @fileName	: RecordMapper.java
 * @author		: koo
 * @date		: 2022. 3. 19.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 19.		  koo			 최초 생성
 *
 */

//interface 작성 -> interface 상속 받은 클래스 작성 -> 클래스 생성 interface 타입으로 저장해서 실행
public interface RecordMapper {
	
	
	// dao에서 작성한 메서드 형식으로 만들어준다.
	// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
	// 주고 받는 데이터 타입 정의, sql 문과 처리 명령문이 필요한다.-> BoardMapper.xml, 
	
	// 메인
	public List<RecordVO> recordList(PageObject pageObject) throws Exception;
	
	// 1-1. 음반리스트
	public List<RecordVO> list(PageObject pageObject) throws Exception;
	// 1-2. 페이지처리(전체 데이터 개수)
	public long getTotalRow(PageObject pageObject) throws Exception;
	// 2-1. 음반 보기
	public RecordVO view(long no) throws Exception;
	// 2-2. 조회수 1증가
	public int increase(long no) throws Exception;
	
	// 3. 음반 등록
	public int write(RecordVO vo) throws Exception;
	// 4-1. 음반 이미지 수정
	public int changeImage(RecordVO vo) throws Exception;
	// 4-2. 음반 음원 수정
	public int changeSong(RecordVO vo) throws Exception;
	// 4-3. 음반 수정
	public int update(RecordVO vo) throws Exception;
	// 5. 음반 삭제
	public int delete(RecordVO vo) throws Exception;
	
	// 6. 음원 리스트 
	public List<RecordVO> songList(PageObject pageObject) throws Exception;

	
	
	

	
} // end of class
