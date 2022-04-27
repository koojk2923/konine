package com.old90.board.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


import lombok.extern.log4j.Log4j;

/**
 * 
 * @packageName	: com.old90.board.aop
 * @fileName	: LogAdvice.java
 * @author		: koo
 * @date		: 2022. 3. 29.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 29.		  koo			 최초 생성
 *
 */

//자동 생성 - @Controller, @Servie, @Repository, @Component, @RestController @~Advice
@Component
//AOP 프로그램으로 선언
@Aspect
@Log4j
public class LogAdvice {

	// AOP로 실행될 메소드 작성
	@Around("execution(* com.old90.*.service.*.*(..))")
	public Object logTimeAndInfo(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("<<<================== 실행 속도와 처리 정보를 출력하는 AOP ====================>>>");
		// 실행 전 처리
		// 1.시작 시간 저장 / 2.실행 클래스 메소드 출력 / 3.넘어가는 데이터 출력 / 4.실행 결과 출력
		
		// 시작 시간 저장 
		long start = System.nanoTime();
		
		// 1.로그로 실행 객체 이름 출력 -> service
		log.info("* 실행하는 객체 : " + pjp.getTarget());
		// 2.로그로 실행 메서드 이름 출력  
		log.info("* 실행 메서드 : " + pjp.getSignature().getName() + "()");
		// 3.로그로 넘어가는 데이터 출력
		log.info("* 넘어가는 데이터 : " + Arrays.toString(pjp.getArgs()));
	
		// 실제적인 처리와 결과 저장 
		Object result = pjp.proceed();
		
		// 실행 후 처리
		// 처리 결과 
		log.info("* 처리된 결과 : " + result);
		
		
		// 끝 시간 저장 / 처리 소요 시간 출력 
		long end = System.nanoTime();
		
		log.info(" * 처리 소요 시간 : " + (end - start) + "Nanosecond");
		
		return result;
	}
}
