package com.greennit.CS3141.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This user class allows one to create posts, edit or delete existing posts, as well as create a new subgreenit.
 * The user can also logout, change password, or delete their account with this class
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "username")
    private String username;
    private int permission;
    private String email;
    private String pass;
    private int karma;

    public User(){
    }

    /*
    Getters and Setters which will be used to set and get values from the database
     */
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPermission(int permission){
        this.permission = permission;
    }
    public int getPermission(){
        return permission;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getPass(){
        return pass;
    }
    public void setKarma(int karma){
        this.karma = karma;
    }
    public int getKarma(){
        return karma;
    }

}
