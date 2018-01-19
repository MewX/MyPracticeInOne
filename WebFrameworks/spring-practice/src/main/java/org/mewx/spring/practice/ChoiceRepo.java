package org.mewx.spring.practice;

import org.mewx.spring.practice.domain.Choice;
import org.mewx.spring.practice.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepo extends JpaRepository<Choice, Long> {
    Choice findById(int id);
    List<Choice> findByQuestion(Question question);
}
