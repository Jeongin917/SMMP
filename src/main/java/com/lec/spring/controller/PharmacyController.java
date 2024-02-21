package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/smmp")
public class PharmacyController {

//    @Autowired
//    private BoardService boardService

    @GetMapping("/pharmacy")
    public void main(){}




}
