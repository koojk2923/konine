package com.old90.util.intercepter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.old90.member.vo.LoginVO;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @packageName	: com.old90.util.intercepter
 * @fileName	: Authrityintercepter.java
 * @author		: koo
 * @date		: 2022. 3. 24.
 * @description : 인터셉터 : 스프링 프레임워크와 Controller 사이에서 동작하게 하는 프로그램
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 24.		  koo			 최초 생성
 *
 */

// 인터셉터 : 스프링 프레임워크와 Controller 사이에서 동작하게 하는 프로그램
// 설명 : Interceptor는 낚아채다의 의미를 가지고 있다. Client에서 Server로 들어온 Request 객체를, Controller의 Handler로 도달하기전,
//		  가로채어, 원하는 추가 작업이나 로직을 수헹 한 후 Handler로 보낼 수 있도록 해주는 Module이다.
//		  Handler : 사용자가 요청한 url에 따라 실행되어야 할 Method
@Log4j
public class Authrityintercepter extends HandlerInterceptorAdapter {
	
	// URL에 대한 권한 정보를 저장하는 Map
	// Map<url, gradeNo>
	private static Map<String, Integer> authMap = new HashMap<>();
	
	// 여기 클래스에서만 사용하는 변수
	private String url = null;
	
	
	// 페이지에 대한 등급 정보를 저장하는 메서드
	// 데이터를 넣는 방법 : static 초기화 블록
	static {
		// 앨범차트 - 등록, 수정, 삭제 - 관리자 : 9
		authMap.put("/record/write.do", 9);
		authMap.put("/record/update.do", 9);
		authMap.put("/record/delete.do", 9);
		
		// 음원차트 - 등록, 수정, 삭제 - 관리자 : 9
		
		// 회원관리 - 등록, 수정, 삭제 - 회원 : 1 | 관리자 : 9
		authMap.put("/member/list.do", 9);
		authMap.put("/member/view.do", 1);
		authMap.put("/member/update.do", 1);
		authMap.put("/member/delete.do", 1);
	} // end of static{}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("+----------------------[Authrityintercepter]------------------------------+");
  
		// JSP의 request와 여기의 request는 같은 것이다. HttpServletRequest가 ServletRequest 상속하고 있다.
		url = request.getServletPath();
		log.info("Authorithy.preHandle.url : " + url);
		
		// 로그인 객체 꺼내기
		// 로그인 정보는 session에 있다. session이 안보인다. request에서 깨낼 수 있다.
		HttpSession session = request.getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");
		
		// 새로운 메세지 객수를 가져오는 처리가 로그아웃 이상이 생긴경우 처리
		// - 브라우저에서 페이지 이동이 없이 가만히 놔눈 상태로 있거나 서버가 재실행이 되면 세션이 사라진다.
		if(url.equals("/ajax/getMessageCnt.do") && vo == null)
			throw new ServletException("DispatcherServlet - 메세지 갯수 가져 오는데 로그인이 안되어 있습니다.");
		
		// 권한이 없는 경우의 처리
		if(!checkAuthority(vo)) {
			// 오류 페이지로 이동시킵니다.
			response.sendRedirect(request.getContextPath() + "/error/auth_error.do");
			// 호출한 쪽으로 되돌아 갑니다. -> 없으면 계속 아애로 실행이 된다.
			return false;
		}
		
		// 요청한 내용을 계속 진행
		return super.preHandle(request, response, handler);
	} // end of preHandle
	
	
	private boolean checkAuthority(LoginVO vo) {
		// URL 정보가 authMap 있는지 확인 한다.
		// 데이터가 없으면(null이면) 권한 체크가 필요없는 페이지 요청 입니다.
		Integer pageGradeNo = authMap.get(url);
		if(pageGradeNo == null) {
			log.info("AuthorityFilter.checkAuthoruty() - 권한이 필요없는 페이지 입니다.");
			return true;
		}
		// 여기서 부터 로그인이 필요한 처리이빈다. vo가 null이면 안된다.!
		if(vo == null) {
			log.info("AuthorityFilter.checkAuthoruty() - 로그인이 필요합니다.");
			return false;
		}
		log.info("\"AuthorityFilter.checkAuthoruty().pageGradeNo : " + pageGradeNo);
		log.info("\"AuthorityFilter.checkAuthoruty().userGradeNo : " + vo.getGradeNo());
		// 권한이 없는 페이지 요청에 대한 처리
		if(pageGradeNo > vo.getGradeNo()) {
			log.info("AuthorityFilter.checkAuthoruty() - 권한이 없습니다.");
			return false;
		}
		log.info("AuthorityFilter.checkAuthoruty() - 권한이 있습니다.");
		return true;		
		
	} // end of checkAuthority()
	
	
} // end of class
