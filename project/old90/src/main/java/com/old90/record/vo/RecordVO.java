package com.old90.record.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * @packageName	: com.old90.record.vo
 * @fileName	: RecordVO.java
 * @author		: koo
 * @date		: 2022. 3. 19.
 * @description : 앨범VO
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 19.		  koo			 최초 생성
 *
 */

@Data
public class RecordVO {

	private long no;				// 곡번호
	private String albumName;		// 앨범병
	private String artistName;		// 아티스트 이름
	private String songTitle;		// 노래제목
	private String genres;			// 장르
	private String introduce;		// 소개글 
	
	// 날짜형 입력을 받을 때 문자열로 들어오므로 패턴을 지정해서 정의해 놓으면 Date 객체로 만들 때 사용한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date relDate;			// 발매일
	
	private String albumImage;		// 앨범이미지사진(파일)
	private String song;			// 음악(파일)
	private long hit;				// 앨범 조회수 
	private long songHit;			// 음원 조회수
	
	// 1-1.이미지 데이터를 받는 변수 선언
	private MultipartFile albumImageFile;		// 앨범 이미지 사진 파일 
	// 1-2.음원 데이터를 받는 변수 선언
	private MultipartFile songFile;				// 음원 파일
	
	// 이미지 바꾸기나 삭제를 할 경우 지워질 파일 정보가 필요한다.
	private String deleteImageFileName;		// 앨범 이미지 사진 파일(삭제)
	private String deleteSongFileName;			// 음원 파일(삭제)
	
	
	private long rnum;				// 음원 순위

	
} // end of class
