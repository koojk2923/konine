package com.old90.member.mapper;
/**
 * 
 * @packageName	: com.old90.member.mapper
 * @fileName	: MemberMapper.java
 * @author		: koo
 * @date		: 2022. 3. 22.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 22.		  koo			 최초 생성
 *
 */

import java.util.List;

import com.old90.member.vo.LoginVO;
import com.old90.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public interface MemberMapper {
	
	// dao에서 작성한 메서드 형식으로 만들어준다.
	// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
	// 주고 받는 데이터 타입 정의, sql 문이 필요하다. -> MemberMapper.xml, 처리 명령문
	
	// 1-1. 리스트
	public List<MemberVO> list(PageObject pageObject) throws Exception;
	// 1-2. 전체 데이터 개수
	public long getTotalRow(PageObject pageObject) throws Exception;
	// 2-1. 보기
	public MemberVO view(String id) throws Exception; // 미완성(정보수정 해야함)
	// 3. 회원가입
	public int write(MemberVO vo) throws Exception;
	// 4. 수정(내정보 수정)
	public int update(MemberVO vo) throws Exception;
	// 5. 탈퇴 
	public int delete(String id) throws Exception;

	// 6. 로그인 처리
	public LoginVO login(LoginVO invo) throws Exception;
	// 7-1. 아이디 중복 체크
	public String idCheck(String id) throws Exception;
	// 7-2. 닉네임 중복 체크
	public String nickNameCheck(String nickName) throws Exception;
	// 8. 상태 변경
	public int changeStatus(MemberVO vo) throws Exception;
	// 9. 등급 변경
	public int changeGradeNo(MemberVO vo) throws Exception;
	
	 
	
	
	
}