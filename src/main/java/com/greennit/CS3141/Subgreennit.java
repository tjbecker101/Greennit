package com.greennit.CS3141;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subgreennits")
/*
 * This subgreennit class is used for inserting and retrieving data from a subgreenit
 */
public class Subgreennit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subgreennit_id")
    private int subgreennit_id;

    private String title;
    private String author;
    private String content;
    private Timestamp creation_date;

    public Subgreennit() {
    }

    // region getters and setters

    public int getThread_id() {
        return subgreennit_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    // endregion
}