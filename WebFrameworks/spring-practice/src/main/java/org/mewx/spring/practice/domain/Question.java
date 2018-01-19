package org.mewx.spring.practice.domain;

import org.mewx.spring.practice.Constants;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "pub_date")
    private Date publishDate;

    // TODO: add choices

    @PrePersist
    protected void onCreate() {
        publishDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPublishDateAsString() {
        return Constants.DATETIME_FORMAT.format(publishDate);
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setpublishDateUsingString(String date) throws ParseException {
        this.publishDate = Constants.DATETIME_FORMAT.parse(date);
    }
}
