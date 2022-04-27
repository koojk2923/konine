package com.BookIng.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




/**
 * Servlet Filter implementation class AuthorityFilter
 */
// @WebFilter("/AuthorityFilter") -> web.xml에 쓸거임
public class AuthorityFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthorityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 전처리 필터

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		// null이면 로그인을 하지않았다
		com.BookIng.member.vo.LoginVO loginVO = (com.BookIng.member.vo.LoginVO) session.getAttribute("login");
		// 사용자 권한
		Integer gradeNo = 0;
		if (loginVO != null)
			gradeNo = loginVO.getGradeNo();
		// 요청 url 가져오기
		String url = req.getServletPath();
		System.out.println("AuthorityFilter.doFilter().url : " + url);

		// Init에서 권한 정보를 꺼내와서 활용해서 처리한다
		Integer pageGradeNo = com.BookIng.util.init.Init.authorityMap.get(url);

		// pageGradeNo == null 이라면 모든 사용자가 이용할 수 있는 페이지
		if (pageGradeNo != null) {
			// 로그인을 하지 않았으면 예외가 발생함
			if (loginVO == null)
				throw new ServletException("로그인이 필요한 페이지입니다.");
			if (gradeNo < pageGradeNo)
				throw new ServletException("페이지에 접근할 권한이 없습니다.");
		}
		System.out.println("확인용");
		// URL에 따른 처리로 간다
		chain.doFilter(request, response);
		// 후처리 필터
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void Init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
