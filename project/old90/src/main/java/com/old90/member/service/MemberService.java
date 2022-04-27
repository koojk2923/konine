package com.old90.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.old90.member.mapper.MemberMapper;
import com.old90.member.vo.LoginVO;
import com.old90.member.vo.MemberVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @packageName	: com.old90.member.service
 * @fileName	: MemberService.java
 * @author		: koo
 * @date		: 2022. 3. 22.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 22.		  koo			 최초 생성
 *
 */


//자동으로 생성이 되게 하는 어노테이션
//@Controller - url과 관련, @Service - 처리, @Repository - DAO, @Compnent - 구성, @RestController - url과 관련 순수데이터(AJAX 사용할때)
@Service
@Log4j
public class MemberService {

	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Inject
	private MemberMapper mapper;
	
	// 1. 회원리스트
	public List<MemberVO> list(PageObject pageObject) throws Exception {
		// 전체 데이터 개수를 구해서 pageOject에 넣는다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 출력
		log.info("1. MemberSerivce.list().pageObject" + pageObject);
		return mapper.list(pageObject);
	} // end of list
	
	// 2. 회원보기
	public MemberVO view(String id) throws Exception {
		log.info("2. MemberService.view().id" + id);
		return mapper.view(id);
	} // end of view
	
	// 3. 회원가입
	public int write(MemberVO vo) throws Exception {
		log.info("3. MemberService.write().vo" + vo);
		return mapper.write(vo);
	} // end of write()
	
	// 4. 수정(내정보 수정)
	public int update(MemberVO vo) throws Exception {
		log.info("4. MemberService.update().vo" + vo);
		return mapper.update(vo);
	} // end of update()
	
	// 5. 탈퇴
	public int delete(String id) throws Exception {
		log.info("5. MemberService.delete().id" + id);
		return mapper.delete(id);
	} // end fo delete() 
		
	// 6. 로그인
	public LoginVO login(LoginVO invo) throws Exception {
		log.info("6. MemberService.login().invo" + invo);
		return mapper.login(invo);
	} // end of login()
	
	// 7-1. 아이디 중복 체크 -> 아이디 를 가져온다.
	public String idCheck(String id) throws Exception {
		log.info("7-1. MemberService.idOrNickNameCheck().id : " + id);
		return mapper.idCheck(id);
	} // end of idOrNickNameCheck()
	
	// 7-2. 아이디 중복 체크 -> 아이디 를 가져온다.
	public String nickNameCheck(String nickName) throws Exception {
		log.info("7-2. MemberService.idOrNickNameCheck().nickName : " + nickName);
		return mapper.nickNameCheck(nickName);
	} // end of idOrNickNameCheck()
	
	// 8. 상태 변경
	public int changeStatus(MemberVO vo) throws Exception {
		log.info("8. MemberService.changeStatus().vo : " + vo);
		return mapper.changeStatus(vo);
	} // end of changeStatus()
	
	// 9. 등급 변경
	public int changeGradeNo(MemberVO vo) throws Exception {
		log.info("9. MemberService.changeGradeNo().vo : " + vo);
		return mapper.changeGradeNo(vo);
	} // end of changeGradeNo()
	
} 
