package com.old90.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @packageName	: com.old90.exception
 * @fileName	: CommanExceptionAdvice.java
 * @author		: koo
 * @date		: 2022. 3. 23.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 23.		  koo			 최초 생성
 *
 */
//자동으로 생성이 되게 하는 어노테이션
@ControllerAdvice
@Log4j
public class CommanExceptionAdvice {

		// Exception 예외가 발생되면 가져와서 처리해 준다. - 500번 오류
		@ExceptionHandler(Exception.class)
		public String except(Exception ex, Model model) {
			
			// 콘솔에 에러 메세지 출력
			log.error("////////////////// 예외처리 ///////////////////");
			log.error("에러 메세지 : " + ex.getMessage());
			
			// JSP 페이지로 예외를 전달하기 위해 model에 담는다.
			// JSP에서 Exception 객체를 EL 객체에서 사용할 수 있도록 Model에 담는다.
			model.addAttribute("exception", ex);
			
			// model 출력
			log.error(model);
			
			// JSP로 이동 시킨다.
			return "error/error_500";
		} // end of except
		
		// 404번 오류
		// 404번 오류 클래스 정의 - 404 예외 클래스 파일을 넘겨서 생성해서 사용하도록 지정
		@ExceptionHandler(NoHandlerFoundException.class)
		// 404번 오류 코드를 클라이언트에 전송
		// 요청에 대한 응답을 할때 상태 코드 값을 넘겨 준다. 정상 : 200, 페이지 없음 : 400
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public String handle404(NoHandlerFoundException ex) {
			
			log.error("////////////////// no Page(404) ///////////////////");
			log.error("요청한 페이지가 없음");
			
			return "error/error_404";
		}
}
