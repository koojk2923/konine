package com.old90.member;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.old90.member.mapper.MemberMapper;
import com.old90.member.vo.LoginVO;
import com.old90.member.vo.MemberVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

/**
 * 
 * 
 * @packageName	: com.old90.member
 * @fileName	: MemberTest.java
 * @author		: koo
 * @date		: 2022. 3. 29.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 29.		  koo			 최초 생성
 *
 */

//@RunWith : Test할 실행 클래스 지정
//JUnit : Spring 모듈 단위 테스트를 하기위한 단위 프로그램이다.  
@RunWith(SpringJUnit4ClassRunner.class)
//DB 설정 파일 지정 -> JAVA만 가지고 URL과 상관없이 실행되므로 설정파이을 직접 지정해 줘야 한다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//syso 대신에 서버 콘솔에 찍거나 로그로 남길때 사용하는 객체 
@Log4j
public class MemberTest {

	// mapper 자동 DI
	@Inject
	private MemberMapper mapper;
		
	// 설정과 자동 DI 테스트	
	@Test
	public void tesDI() {
		log.info("---------------------- 자동 DI Test ----------------------------");
		log.info(mapper + "\n");
	}
		
	// 회원 리스트 mapper 테스트
	@Test
	public void testList() throws Exception {
		PageObject pageObject = new PageObject();
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("---------------------- 회원 리스트 mapper Test ----------------------------");
		log.info(mapper.list(pageObject) + "\n");
		
	}
	
	// 회원 정보보기 - 글번호
	@Test
	public void testView() throws Exception {
		String id = "test";
		log.info("----------------------  회원 정보 보기 mapper Test ----------------------------");
		log.info(mapper.view(id) + "\n");
		
	}
	
	// 회원가입 
	@Test
	public void testWrite() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setId("test999");
		vo.setNickName("test@");
		vo.setPw("1111");
		vo.setName("테스트");
		vo.setGender("남자");
		vo.setBirth(new Date(2022,04,28));
		vo.setTel("010-1111-1111");
		vo.setEmail("test@test.com");
		log.info("---------------------- 회원 가입 mapper Test ----------------------------");
		log.info(mapper.write(vo) + "\n");
		
	}

	// 회원정보수정  -
	@Test
	public void testUpdate() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setId("test@@");
		vo.setNickName("test@@");
		vo.setPw("1111");
		vo.setName("테스트");
		vo.setGender("남자");
		vo.setBirth(new Date(2022,03,28));
		vo.setTel("010-1111-1111");
		vo.setEmail("test@test.com");
		log.info("---------------------- 회원 정보 수정 mapper Test ----------------------------");
		log.info(mapper.update(vo) + "\n");
		
	}
	
	// 회원탈퇴
	@Test
	public void testDelete() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setId("test@@");
		
		log.info("---------------------- 회원 탙퇴 mapper Test ----------------------------");
		log.info(mapper.delete("test@@"));
		
	}		
	
	// 로그인 처리
	@Test
	public void testLogin() throws Exception {
		LoginVO vo = new LoginVO();
		vo.setId("test@@");
		vo.setPw("1111");
		log.info("---------------------- 로그인 처리 mapper Test ----------------------------");
		log.info(mapper.login(vo));
	}
}
