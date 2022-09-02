package com.test.newsmanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @Column
    private String text;

    @Column
    private String username;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_news")
    private News news;

    {
        date = LocalDateTime.now();
    }

    public Comment(String text, String username, News news) {
        this.text = text;
        this.username = username;
        this.news = news;
    }

    public Comment(LocalDateTime date, String text, String username, News news) {
        this.date = date;
        this.text = text;
        this.username = username;
        this.news = news;
    }
}
