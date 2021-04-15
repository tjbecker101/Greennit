package com.greennit.CS3141.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int host;
    private String title;
    private String author;
    private String content;
    private Timestamp creation_date;
    private int likes;

    public Thread() {
    }

    // region getters and setters

    public int getHost() {
        return host;
    }

    public void setHost(int host_subgreennit) {
        this.host = host_subgreennit;
    }

    public int getId() {
        return id;
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

    public int getLikes(){ return likes;}

    public void setLikes(int likes){ this.likes = likes;}

    public String getTimeAgo(){
        long difference = System.currentTimeMillis() - this.creation_date.getTime();

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

