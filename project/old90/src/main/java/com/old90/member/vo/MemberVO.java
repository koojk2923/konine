package com.old90.member.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 
 * @packageName	: com.webjjang.member.vo
 * @fileName	: MemberVO.java
 * @author		: koo
 * @date		: 2022. 3. 11.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 11.		  koo			 최초 생성
 *
 */

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String nickName;
	private String gender;
	// 날짜형 입력을 받을 때 문자열로 들어오므로 패턴을 지정해서 정의해 놓으면 Date 객체로 만들 때 사용한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private String tel;
	private String email;
	private Date regDate; 
	private Date conDate;
	private String status;
	private int gradeNo;
	private String gradeName;
	
	// 사용자가 올린 사진을 저장하는 변수
	// 회원가입 폼의 jsp에서 name="photoFile"로 준다.
	// post이고 enctype="multipart/form-data"로 지정해야만 한다.
//	private MultipartFile photoFile;
	
}
