package com.mayby.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("faq/*")
public class FaQControllerImpl implements FaQController{

    /* FaqList */
    @Override
    @GetMapping("faqList")
    public void faqList() throws Exception {

    }


}
