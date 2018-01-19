package org.mewx.spring.practice.controller;

import org.mewx.spring.practice.ChoiceRepo;
import org.mewx.spring.practice.domain.Choice;
import org.mewx.spring.practice.domain.Question;
import org.mewx.spring.practice.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainPage {
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ChoiceRepo choiceRepo;

    @RequestMapping("/")
    public String index(Model model) {
        List<Question> questions = questionRepo.findAll();
        model.addAttribute("questions", questions);
        return "index";
    }

    @RequestMapping(value = "/detail/{id:[\\d]+}", method = GET)
    public String detail(Model model, @PathVariable("id") int id) {
        Question question = questionRepo.findById(id);
        if (question == null) {
            model.addAttribute("error_message", "Bad id!");
            return "details";
        }

        List<Choice> choices = choiceRepo.findByQuestion(question);
        model.addAttribute("question_text", question.getQuestionText());
        model.addAttribute("choices", choices);
        return "details";
    }

}
