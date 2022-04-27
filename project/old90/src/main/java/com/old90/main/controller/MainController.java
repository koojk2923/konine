package com.old90.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.old90.record.service.RecordService;
import com.webjjang.util.PageObject;

@Controller
@RequestMapping("/main")
public class MainController {

    // 자동 DI 적용
    @Autowired
    @Qualifier("RecordService")
    private RecordService recordService;
//    @Autowired
//    @Qualifier("ImageService")
//    private ImageService imageService;

    @GetMapping("/main.do")
    public String main(Model model) throws Exception {
        PageObject pageObject = new PageObject(1, 9); // 1페이지에 7개를 담겠다.
        recordService.recordList(pageObject);
        model.addAttribute("recordList", recordService.recordList(pageObject));

//        pageObject = new PageObject(1, 4);
//        imageService.imageList(pageObject);
//        model.addAttribute("imageList", imageService.imageList(pageObject));

        return "main/main";
    }
}
