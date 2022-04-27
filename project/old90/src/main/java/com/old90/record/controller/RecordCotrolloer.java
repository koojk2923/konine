package com.old90.record.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.old90.record.service.RecordService;
import com.old90.record.vo.RecordVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;


/**
 * 
 * @packageName	: com.old90.record.controller
 * @fileName	: RecordCotrolloer.java
 * @author		: koo
 * @date		: 2022. 3. 19.
 * @description : 앨범 Controller
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 19.		  koo			 최초 생성
 *
 */

//자동으로 생성이 되게 하는 어노테이션
//@Controller - url과 관련, @Service - 처리, @Repository - DAO, @Compnent - 구성, @RestController - url과 관련 순수데이터(AJAX 사용할때)
@Controller
@RequestMapping("/record")
@Log4j
public class RecordCotrolloer {

	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Autowired
	private RecordService service;
	
	// song 변수 선언
	private final String MODULE = "record";
	
	
	// 1.음반리스트
	@GetMapping("/list.do")
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다. 파라메터로 모델 객체를 전달 받아서 사용한다.
	public String list(PageObject pageObject, Model model) throws Exception {
		// 페이지가 1보다 작으면 1페이지로 세팅해 준다.
		if(pageObject.getPage() < 1)
			pageObject.setPage(1);
		// 1페이지당 보여지는 개수는 9개
		// perPageNum == 10이면 8로 고치자. perPageNum - 4, 8, 12, 16 --> 기본 한페이지의 데이터 개수는 9로 세팅한다.
		if(pageObject.getPerPageNum() == 10) pageObject.setPerPageNum(9);
		
		// 출력학인
		log.info("1.RecordController.list().pageObject" + pageObject);
		
		// model에 넣어주면 request에 담는다.
		model.addAttribute("list", service.list(pageObject));
		// list.jsp 를 보여준다.
		return MODULE + "/list";
		
	} // end of list()
	
	// 2. 음반 보기
	@GetMapping("/view.do")
	public String view(long no, int inc, Model model, PageObject pageObject) throws Exception {
		
		log.info("2.RecordController.view().no,inc - " + no + ", " + inc);
		RecordVO vo = service.view(no, inc);
		// 글 내용 중에서 줄바꿈처리 해야만 한다. \n -> <br>로 바꾼다.
		vo.setGenres(vo.getGenres().replace("\n", "<br>"));
		
		model.addAttribute("vo", vo);
		
		return MODULE + "/view";
	} // end of view()
	
	// 3-1. 음반등록 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		return MODULE + "/write";
	} // end of writeForm()
	
	// 3-2. 음반등록
	@PostMapping("/write.do")
	public String write(RecordVO vo, int perPageNum, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		
		// 패치 선언
		String path_1 = "/upload/image";	// 앨범 이미지 파일 경로
		String path_2 = "/upload/song";		// 음원 파일 경로
		
		// 파일 저장한 후 vo 객체 저장 파일의 정보를 저장해 놓는다.
		// 서버에 파일을 업로드 한다. -> DB에 저장할 파일정보가 나온다.
		vo.setAlbumImage(FileUtil.upload(path_1, vo.getAlbumImageFile(), request));		// 이미지파일
		vo.setSong(FileUtil.upload(path_2, vo.getSongFile(), request));					// 음원파일
		
		// 데이터 확인
		log.info("3.RecordController.write().vo -> " + vo);
		
		// DB에 정보 저장
		service.write(vo);
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "음반이 등록 되었습니다.");

		// 프로그램 재우기 - 2초 재우기
		// 이미지 게시판 리스트로 갈때 파일 처리가 마무리 되지 않은 상태에서 표시하라고 하면 새로 등록한 파일만 보이지 않게 된다.
		Thread.sleep(2000);
		
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	} // end of write()
	
	
	
	// 4-1. 음반수정 폼
	@GetMapping("/update.do")
	public String updateForm(long no, PageObject pageObject, Model model) throws Exception{
		log.info("4-1.RecordController.updaetForm().no -> " + no);
		
		model.addAttribute("vo", service.view(no, 0));
		
		return MODULE + "/update";
	} // end of updateForm()
	
	
	// 4-2. 앨범이미지 사진 바꾸기 - 번호(vo), 지울파일이름(vo : 원래파일정보), 바꿀파일(vo), 페이지(pageObejct), 한 페이지당 갯수(pageObejct)
	@PostMapping("/changeImage.do")
	public String changeImage(RecordVO vo, PageObject pageObject, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		
		// 데이터 확인
		log.info("4-2.RecordController.changeImage().vo" + vo);
		
		// 패치 선언
		String path_1 = "/upload/image";	// 앨범 이미지 파일 경로
		
		// 1.새로운 파일 올리기 (파일 저장한 후 vo 객체 저장 파일의 정보를 저장해 놓는다.)
		// 서버에 파일을 업로드 한다. -> DB에 저장할 파일정보가 나온다.
		vo.setAlbumImage(FileUtil.upload(path_1, vo.getAlbumImageFile(), request));		// 이미지 파일
		
		// 2. DB정보 수정
		service.changeImage(vo);
		
		// 3.원래 파일 지우기
		// FileUtil.getRealPath(path, 지우려는파일정보, request) --> 실제적인 위치를 잡는다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteImageFileName(), request));
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "이미지 사진을 바꿨습니다.");
		
		// 프로그램 재우기 - 1초 재우기
		// 이미지 게시판 리스트로 갈때 파일 처리가 마무리 되지 않은 상태에서 표시하라고 하면 새로 등록한 파일만 보이지 않게 된다.
		Thread.sleep(1000);
		
		return "redirect:update.do?no=" + vo.getNo() + "&inc=0"
		+ "&page=" + pageObject.getPage()
		+ "&perPageNum=" + pageObject.getPerPageNum();
	
	} // end of changeImage()
	
	// 4-3. 음원파일 바꾸기 - 번호(vo), 지울파일이름(vo : 원래파일정보), 바꿀파일(vo), 페이지(pageObejct), 한 페이지당 갯수(pageObejct)
	@PostMapping("/changeSong.do")
	public String changeSong(RecordVO vo, PageObject pageObject, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		
		// 데이터 확인
		log.info("4-3.RecordController.changeImage().vo" + vo);
		
		// 패치 선언
		String path_2 = "/upload/song";
		
		// 1.새로운 파일 올리기 (파일 저장한 후 vo 객체 저장 파일의 정보를 저장해 놓는다.)
		// 서버에 파일을 업로드 한다. -> DB에 저장할 파일정보가 나온다.		
		vo.setSong(FileUtil.upload(path_2, vo.getSongFile(), request));
		
		// 2. DB 정보 수정
		service.changeSong(vo);
		
		// 3.원래 파일 지우기
		// FileUtil.getRealPath(path, 지우려는파일정보, request) --> 실제적인 위치를 잡는다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteSongFileName(), request));
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "음원을 바꿨습니다.");
		
		// 프로그램 재우기 - 1초 재우기
		// 이미지 게시판 리스트로 갈때 파일 처리가 마무리 되지 않은 상태에서 표시하라고 하면 새로 등록한 파일만 보이지 않게 된다.
		Thread.sleep(1000);
		
		return "redirect:update.do?no=" + vo.getNo() + "&inc=0"
		+ "&page=" + pageObject.getPage()
		+ "&perPageNum=" + pageObject.getPerPageNum();
	} // end of changeSong()
	
	// 4-4. 음반수정 처리 - 앨범명, 아티스트명, 노래명, 소개글, 장르, 발매일
	@PostMapping("/update.do")
	public String update(RecordVO vo, PageObject pageObject, RedirectAttributes rttr) throws Exception {
		
		// 데이터 확인
		log.info("4-4.RecordController.update().vo" + vo);

		// DB정보 수정
		service.update(vo);
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "음반 정보가 수정 되었습니다.");
		
		return "redirect:view.do?no=" + vo.getNo() + "&inc=0"
		+ "&page=" + pageObject.getPage()
		+ "&perPageNum=" + pageObject.getPerPageNum();
		
	} // end of update()
	
	// 5. 음원 삭제
	@GetMapping("/delete.do")
	public String delete(RecordVO vo, PageObject pageObject, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		
		// 데이터 확인
		log.info("4.RecordController.delete().vo" + vo);
	
		
		// DB에서 데이터 삭제
		service.delete(vo);
		
		// 서버의 파일을 삭제
		// 3.원래 파일 지우기
		// FileUtil.getRealPath(path, 지우려는파일정보, request) --> 실제적인 위치를 잡는다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteImageFileName(), request));	// 앨범 이미지 삭제 
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteSongFileName(), request));		// 음원 파일 삭제
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "이미지가 삭제 되었습니다.");
		
		
		return "redirect:list.do?perPageNum=" + pageObject.getPerPageNum();
	} // end of delete()
	


} // end of class
