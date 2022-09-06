package com.test.newsmanagement.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private String text;

    @Column
    private String username;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_news")
    private News news;

    public Comment(LocalDate date, String text, String username, News news) {
        this.date = date;
        this.text = text;
        this.username = username;
        this.news = news;
    }
}
