package com.old90.board;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.old90.board.mapper.BoardMapper;
import com.old90.board.vo.BoardVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

/**
 * 
 * 
 * @packageName	: com.old90.board
 * @fileName	: BoardTest.java
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
public class BoardTest {

		// mapper 자동 DI
		@Inject
		private BoardMapper mapper;
		
		// 설정과 자동 DI 테스트 
		@Test
		public void testDI() {
			log.info("---------------------- 자동 DI Test ----------------------------");
			log.info(mapper + "\n");
		}
		
		// 게시판 리스트 mapper 테스트
		@Test
		public void testList() throws Exception {
			PageObject pageObject = new PageObject();
			pageObject.setTotalRow(mapper.getTotalRow(pageObject));
			log.info("---------------------- 게시판 리스트 mapper Test ----------------------------");
			log.info(mapper.list(pageObject) + "\n");
			
		}
		
		// 게시판 글보기 - 글번호
		@Test
		public void testView() throws Exception {
			long no = 69;
			log.info("---------------------- 게시판 글보기 mapper Test ----------------------------");
			log.info(mapper.view(no) + "\n");
			
		}
		
		// 게시판 글쓰기 - 제목, 내용, 작성자 -> vo
		@Test
		public void testWrite() throws Exception {
			BoardVO vo = new BoardVO();
			vo.setTitle("테스트 진행");
			vo.setContent("테스트 진행 중");
			vo.setWriter("개발자");
			log.info("---------------------- 게시판 글쓰기 mapper Test ----------------------------");
			log.info(mapper.write(vo) + "\n");
			
		}
		
		// 게시판 글수정 - 번호, 제목, 내용, 작성자
		@Test
		public void testUpdate() throws Exception {
			BoardVO vo = new BoardVO();
			vo.setNo(45);
			vo.setTitle("Test 진행");
			vo.setContent("테스트 진행 중");
			vo.setWriter("개발자");
			log.info("---------------------- 게시판 글수정 mapper Test ----------------------------");
			log.info(mapper.update(vo) + "\n");
			
		}
		
		// 게시판 글삭제 - 글번호
		@Test
		public void testDelete() throws Exception {
			long no = 69;
			log.info("---------------------- 게시판 글삭제  mapper Test ----------------------------");
			log.info(mapper.delete(no) + "\n");
			
		}		
		
}
