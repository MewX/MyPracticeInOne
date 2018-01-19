package org.mewx.spring.practice.controller;

import org.mewx.spring.practice.domain.Question;
import org.mewx.spring.practice.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class Homepage {
    @Autowired
    private QuestionRepo questionRepo;

    @RequestMapping("/")
    public String index(Model model) {
        List<Question> questions = questionRepo.findAll();
        model.addAttribute("questions", questions);
        return "index";
    }
}
