package com.old90.util.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class AuthorithyError {

	@GetMapping("/auth_error.do")
	public String main() throws Exception {
		return "error/auth_error";
		
	}
}
