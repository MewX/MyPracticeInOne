package org.mewx.spring.practice;

import org.mewx.spring.practice.domain.Choice;
import org.mewx.spring.practice.domain.Question;
import org.mewx.spring.practice.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ChoiceRepo choiceRepo;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if (!Constants.LOAD_DATA) return;

        // load initial data
        Question firstQuestion = new Question(); // the publish date is automatically updated
        firstQuestion.setQuestionText("Sample question 1?");
        questionRepo.save(firstQuestion);

        Choice choice1 = new Choice(firstQuestion, "Choice 1");
        choiceRepo.save(choice1);
        Choice choice2 = new Choice(firstQuestion, "Choice 2");
        choiceRepo.save(choice2);
        Choice choice3 = new Choice(firstQuestion, "Choice 3");
        choiceRepo.save(choice3);
    }
}
