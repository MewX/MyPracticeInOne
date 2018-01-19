package org.mewx.spring.practice.domain;

import javax.persistence.*;

@Entity
@Table(name = "choice")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "question_id")
    @ManyToOne
    private Question question;

    @Column(name = "text")
    private String text;

    @Column(name = "votes")
    private int votes; // TODO: make it atomic increase-able

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
