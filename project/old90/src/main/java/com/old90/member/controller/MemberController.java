package com.old90.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.old90.member.service.MemberService;
import com.old90.member.vo.LoginVO;
import com.old90.member.vo.MemberVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

/**
 * 
 * @packageName	: com.old90.member.controller
 * @fileName	: MemberController.java
 * @author		: koo
 * @date		: 2022. 3. 22.
 * @description : 
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 22.		  koo			 최초 생성
 *
 */

//자동으로 생성이 되게 하는 어노테이션
//@Controller - url과 관련, @Service - 처리, @Repository - DAO, @Compnent - 구성, @RestController - url과 관련 순수데이터(AJAX 사용할때)
@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Autowired
	private MemberService service;
	
	// member 변수 선언
	private final String MODULE = "member";
	
	
	// 1.회원리스트(관리자만 가능하다.)
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception {
		
		model.addAttribute("list", service.list(pageObject));
		
		// 데이터 확인
		log.info("1. MemberController.list().pageObejt" + pageObject);
		
		return MODULE + "/list";
	} // end of list()
	
	// 2.회원정보보기 / 내 정보보기
	@GetMapping("/view.do")
	public String view(String id, Model model, HttpSession session, PageObject pageObject)  throws Exception {
		
		if(id == null) {
			// 내 정보 보기
			model.addAttribute("title", "내 정보 보기");
			id = ((LoginVO) session.getAttribute("login")).getId();
		} else {		
			// 회원관리 - 회원 정보보기
			model.addAttribute("title", "회원 정보 보기");
		} // end of id
		
		// 데이터 확인
		log.info("2. MemberController.view().id : " + id);
		
		model.addAttribute("vo", service.view(id));
		return MODULE + "/view"; 
	} // end of view()
	
	// 3-1. 회원 가입 폼
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		
		// 데이터 확인
		log.info("3-1. MemberController.writeForm()");
		return MODULE + "/write";
	} // end of wrtieForm()
	
	// 3-2. 회원 가입 처리
	@PostMapping("/write.do")
	public String write(MemberVO vo, RedirectAttributes rttr) throws Exception {
		
		// 회원 가입 처리
		service.write(vo);
		
		// 데이터 확인
		log.info("3-2. MemberController.write().vo : " + vo);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 회원가입이 되셨습니다. \\n 로그인 후 사용하세요.");
		
		return "redirect:login.do";		
	} // end of write()
	
	// 4-1. 수정폼(내 정보 수정)
	@GetMapping("/update.do")
	public String upateForm(String id, Model model, HttpSession session) throws Exception {
		
		if(id == null) {
		id = ((LoginVO) session.getAttribute("login")).getId();
		}

		// 데이터 확인
		log.info("4-1. MemberController.updateForm().vo : " + id);
		
		model.addAttribute("vo", service.view(id));
		
		return MODULE + "/update";
	} // end of updateForm()
	
	// 4-2. 수정 처리
	@PostMapping("/update.do")
	public String update(MemberVO vo, RedirectAttributes rttr,PageObject pageObject) throws Exception {
		
		// 데이터 확인
		log.info("4-2. MemberController.update().vo" + vo);
		
		// DB정보 수정
		service.update(vo);
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "내 정보가 수정 되었습니다.");
		
		return "redirect:view.do?id=" + vo.getId() 
			+ "&page=" + pageObject.getPage()
			+ "&perPageNum=" + pageObject.getPerPageNum();

	} // end of update()
	
	// 5. 회원 탈퇴
	@GetMapping("/delete.do")
	public String delete(String id, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		// DB정보 삭제
		service.delete(id);
		
		// 로그아웃 처리 - session의 정보를 지운다.
		session.removeAttribute("login");
		log.info("로그아웃 처리 - 삭제후 vo : " + session.getAttribute("login"));
		
		// 데이터 확인
		log.info("5. MemberController.delete().vo : " + id);
		
		// 처리 결과 메세지 저장
		rttr.addFlashAttribute("msg", "회원 탈퇴가 되었습니다.");
		
		return "redirect:/main/main.do";
			
	} // end of delete()
	
	
	// 7-1 로그인폼
	@GetMapping("/login.do")
	public String loginForm() throws Exception {
		log.info("7-1. MemberController.loginForm() + login폼으로 이동");
		return MODULE + "/login";
	} // end of loginForm()
	
	// 7-2. 로그인 처리 - session을 이용한다.
	// 사용자가 아이디와 비밀번호를 입력해서 보낸다. -> 받는다.
	@PostMapping("/login.do")
	public String login(LoginVO invo, HttpSession session) throws Exception {
		
		// 데이터 확인
		log.info("7-2. MemberController.login().invo" + invo);
		
		// DB에서 입력한 정보에 맞는 데이터를 가져 온다.
		LoginVO loginVO = service.login(invo);
		
		// loginVO가 null : 아이디와 비밀번호가 틀력서 데이터를 가져 올 수 없다.
		if(loginVO == null) throw new Exception("아이디나 비밀번호를 확인해 주세요.");
		
		session.setAttribute("login", loginVO);
		
		// 추후 수정 해야함!!
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/main/main.do";				// 메인으로 수정
	} // end of login()
	
	// 8. 로그아웃 처리 - ( Service , Mapper 없음 세선에서 가져와서 처리한다.)
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		log.info("로그아웃 처리 - 삭제전 vo :" + session.getAttribute("login"));
		// 로그아웃 처리 - session의 정보를 지운다.
		session.removeAttribute("login");
		log.info("로그아웃 처리 - 삭제후 vo : " + session.getAttribute("login"));
		
		// 추후 수정 해야함!!
		// 원래는 main으로 보내야하나 main을 안 만들어서 만들어진 게시판으로 임시로 보낸다.
		return "redirect:/main/main.do";		// 메인으로 수정	
	} // end of logout()
	
	// 9-1. 아이디 중복 체크
	@GetMapping("/idCheck")
	public String idCheck(String id, Model model) throws Exception {
		
		model.addAttribute("id", service.idCheck(id));
		
		// 데이터 확인
		log.info("9-1. MemberController.idCheck().id" + id);
		
		return MODULE + "/idCheck";
	} // end of idCheck()
	
	// 9-2. 닉네임 중복 체크
	@GetMapping("/nickNameCheck")
	public String nickNameCheck(String nickName, Model model) throws Exception {
		
		model.addAttribute("nickName", service.nickNameCheck(nickName));
		
		// 데이터 확인
		log.info("9-2. MemberController.nickNameCheck().nickName" + nickName);
		
		return MODULE + "/nickNameCheck";
	} // end of nickNameCheck()
	
	// 10. 상태 변경
	@PostMapping("/changeStatus.do")
	public String changeStatus(MemberVO vo, PageObject pageObject) throws Exception {
		 
		// 데이터 확인
		log.info("10. MemberController.changeStatus().vo" + vo);
		// DB에서 상태 변경을 시킨다.
		service.changeStatus(vo);
		
		return "redirect:view.do?id=" + vo.getId()
			+ "&page=" + pageObject.getPage()
			+ "&perPageNum" + pageObject.getPerPageNum();
	} // end of changeStatus()
	
	// 11. 등급 변경
	@PostMapping("/changeGradeNo.do")
	public String changeGradeNo(MemberVO vo, PageObject pageObject) throws Exception {
		
		// 데이터 확인
		log.info("11. MemberController.changeGradeNo().vo" + vo);
		
		// DB에서 상태 변경을 시킨다.
		service.changeGradeNo(vo);
		
		return "redirect:view.do?id=" + vo.getId()
		+ "&page=" + pageObject.getPage()
		+ "&perPageNum" + pageObject.getPerPageNum();
	} // end of changeGradeNo()
	
	
	
} // end of class
