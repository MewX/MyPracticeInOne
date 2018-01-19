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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/detail/{id:[\\d]+}.html", method = GET)
    public String detail(Model model, @PathVariable("id") int id) {
        Question question = questionRepo.findById(id);
        if (question == null) {
            model.addAttribute("error_message", "Bad id!");
            return "details";
        }

        List<Choice> choices = choiceRepo.findByQuestion(question);
        choices.sort(Comparator.comparingInt(Choice::getId));

        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        return "details";
    }

    @RequestMapping(value = "/vote/{id:[\\d]+}.do", method = POST)
    public String vote(Model model, @PathVariable("id") int questionId, @RequestParam(value = "choice", required = false) Integer choiceId) {
        Question question = questionRepo.findById(questionId);

        if (choiceId == null) choiceId = -1;
        Choice choice = choiceRepo.findById(choiceId);

        if (question == null || choice == null || choice.getQuestion().getId() != question.getId()) {
            model.addAttribute("error_message", "Bad request!");
            return "details";
        } else {
            // TODO: change to atomic method
            choice.setVotes(choice.getVotes() + 1);
            choiceRepo.save(choice);

            return "redirect:/result/" + questionId + ".html";
        }
    }

    @RequestMapping(value = "/result/{id:[\\d]+}.html", method = GET)
    public String result(Model model, @PathVariable("id") int id) {
        Question question = questionRepo.findById(id);
        if (question == null) {
            return "redirect:/";
        }

        List<Choice> choices = choiceRepo.findByQuestion(question);
        choices.sort(Comparator.comparingInt(Choice::getId));

        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        return "results";
    }
}
