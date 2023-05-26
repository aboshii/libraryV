package com.library.springlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HelloController {
    @GetMapping("/welcome")
    @ResponseBody
    String home(@RequestParam
                        (value = "name", required = false, defaultValue = "stranger") String name,
                @RequestHeader(name = "User-Agent") String userAgent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome in LibraryV ");
        if (name != null) {
            sb.append(name + System.getProperty("line.separator"));
        }
        sb.append("Używasz przeglądarki: \n" + userAgent);
        System.out.println(sb);
        return sb.toString();
    }

    @GetMapping("/login")
    String login() {
        return "login-form";
    }
}