package com.greennit.CS3141.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "subgreennits")
/*
 * This subgreennit class is used for inserting and retrieving data from a subgreenit
 */
public class Subgreennit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private String description;

    public Subgreennit() {
    }

    // region getters and setters

    public int getSubgreennit_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String content) {
        this.description = description;
    }

    // endregion
}