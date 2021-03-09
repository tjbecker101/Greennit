package com.greennit.CS3141.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
/*
 * This post class is used for inserting and retrieving data from a post such as:
 * The thread the post belongs to, the id of the post, the author and content of the post,
 * the time the post was created, and how many likes the post has.
 */
public class Post implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private int host_thread;
    private String author;
    private String content;
    private Timestamp creation_date;
    private int likes;
    private int parent_id;

    public Post(){

    }

    // region getters and setters
    public int getHost_thread() {
        return host_thread;
    }

    public void setHost_thread(int host_thread) {
        this.host_thread = host_thread;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    // endregion


}
