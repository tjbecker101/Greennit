package com.greennit.CS3141;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "threads")
/*
 * This thread class is used for inserting and retrieving data from a thread such as:
 * The subgreennit the thread is on, the id of the thread, the title and author of the thread,
 * the content of the thread, and the time the thread was created.
 */
public class Thread implements Serializable {
    @Id
    private int host_subgreennit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int thread_id;

    private String title;
    private String author;
    private String content;
    private Timestamp creation_date;

    public Thread() {
    }

    public Thread(int host_subgreennit, int thread_id) {
        this.host_subgreennit = host_subgreennit;
        this.thread_id = thread_id;
    }

    // region getters and setters

    public int getHost_subgreennit() {
        return host_subgreennit;
    }

    public void setHost_subgreennit(int host_subgreennit) {
        this.host_subgreennit = host_subgreennit;
    }

    public int getThread_id() {
        return thread_id;
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

