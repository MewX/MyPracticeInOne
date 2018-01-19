package org.mewx.spring.practice.repository;

import org.mewx.spring.practice.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    Question findById(int id);
}
