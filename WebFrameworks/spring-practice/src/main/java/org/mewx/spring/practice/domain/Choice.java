package org.mewx.spring.practice.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "choice")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "question_id")
    @ManyToOne(targetEntity = Question.class)
    @NotNull
    private Question question;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @Column(name = "votes")
    private int votes = 0; // TODO: make it atomic increase-able

    public Choice(Question q, String text) {
        question = q;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
