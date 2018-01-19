package org.mewx.spring.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homepage {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
