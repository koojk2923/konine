package com.old90.member.vo;

import lombok.Data;

@Data
public class LoginVO {

	private String id, pw, name, nickName;
	private int gradeNo;
	private String gradeName;
	// 새로운 메시지를 저장하는 변수 -> 서브쿼리
	// 메세지 시스템에서 새 메세지를 읽으면 세션에 있는 새메세지 정보를 -1 처리를 해 줘야한다.
	private long newMessage;
}
