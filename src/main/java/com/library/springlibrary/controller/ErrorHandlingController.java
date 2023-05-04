package com.library.springlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorHandlingController {
    @GetMapping("/error")
    String error(){
        return "error.html";
    }
}
