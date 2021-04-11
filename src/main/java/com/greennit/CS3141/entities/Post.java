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
    private Timestamp time_posted;
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

    public Timestamp getTime_posted() {
        return time_posted;
    }

    public void setTime_posted(Timestamp time_posted) {
        this.time_posted = time_posted;
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

    /*
     * This method converts the timestamp into a format such as "6 days ago" or "1 minute ago"
     */
    public String getTimeAgo(){
        long difference = System.currentTimeMillis() - this.time_posted.getTime();

        String unit; //holds units of time
        long diffResult = 0; //holds final result

        if (difference >= 86400000) { //larger than a day
            diffResult = difference / 86400000; //Converts to days
            unit = "Day";
            if (diffResult >= 365) { //larger than a year
                diffResult = diffResult / 365;
                unit = "Year";
            } else if (diffResult >= 30){ //larger than a month
                diffResult = diffResult / 30;
                unit = "Month";
            } else if (diffResult >= 7){ //larger than a week
                diffResult = diffResult / 7;
                unit = "Week";
            }
        } else if (difference >= 3600000){ //larger than an hour
            diffResult = difference / 3600000; //Converts to hours
            unit = "Hour";
        } else { //display in minutes
            diffResult = difference / 60000; //Converts to minutes
            unit = "Minute";
        }

        if (diffResult > 1){ //make plural if need be
            unit = unit + "s";
        }

        return diffResult + " " + unit + " Ago";
    }

    // endregion


}
