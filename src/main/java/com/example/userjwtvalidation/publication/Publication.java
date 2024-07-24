package com.example.userjwtvalidation.publication;

import com.example.userjwtvalidation.user.User;
import jakarta.persistence.*;
@Table(name = "publication")
@Entity
public class Publication {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String content;


    public Publication() {
    }

    public Publication(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {

        return title;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
