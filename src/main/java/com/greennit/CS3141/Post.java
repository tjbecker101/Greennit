package com.greennit.CS3141;

import java.time.LocalDateTime;

public class Post {
    private String content;
    private String author;
    private int likes;
    private LocalDateTime timePosted;

    public Post(String content, String author) {
        this.content = content;
        this.author = author;
        likes = 0;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }
}
