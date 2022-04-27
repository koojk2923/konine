package com.old90.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.old90.record.service.RecordService;
import com.webjjang.util.PageObject;


/**
 * 
 * @packageName	: com.old90.song.controller
 * @fileName	: SongController.java
 * @author		: koo
 * @date		: 2022. 3. 28.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 28.		  koo			 최초 생성
 *
 */

//자동으로 생성이 되게 하는 어노테이션
//@Controller - url과 관련, @Service - 처리, @Repository - DAO, @Compnent - 구성, @RestController - url과 관련 순수데이터(AJAX 사용할때)
@Controller
@RequestMapping("/song")
public class SongController {

	// 자동 DI 적용
	@Autowired
	@Qualifier("RecordService")
	private RecordService recordService;
	
	@GetMapping("/list.do")
	public String main(Model model, PageObject pageObject) throws Exception {
//		PageObject pageObject = new PageObject(1, 10); // 1페이지에 10개를 담겠다.	
		
		if(pageObject.getPage() < 1)
			pageObject.setPage(1);
		
		recordService.songList(pageObject);
		model.addAttribute("songList", recordService.songList(pageObject));
		
		return "song/list";
	}
	
	
	
} // end of class
