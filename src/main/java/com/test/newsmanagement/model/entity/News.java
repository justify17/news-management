package com.test.newsmanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "date", "title", "text"})
@ToString(of = {"id", "date", "title", "text"})
@NoArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @Column
    private String title;

    @Column
    private String text;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    {
        date = LocalDateTime.now();
        comments = new ArrayList<>();
    }

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public News(LocalDateTime date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
}