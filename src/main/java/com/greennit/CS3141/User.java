package com.greennit.CS3141;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This user class allows one to create posts, edit or delete existing posts, as well as create a new subgreenit.
 * The user can also logout, change password, or delete their account with this class
 */
public class User{

    private String username;
    private int karma;
    private String password;

    private User(String username, String password){
        this.username = username;
        karma = getKarma(username);
        this.password = password;
    }

    /**
     * Gets the karma tied to the username
     * @param username - key for the database to get the karma
     * @return - karma for the user
     */
    private int getKarma(String username){
        return 0;
    }

    private void createPost(Post post){

    }

    private void editPost(Post post){

    }

    private void deletePost(Post post) {

    }

    private void createSubgreenit(String name){

    }

    private String changePassword(String password){
        return null;
    }

    private void deleteAccount(){

    }

    private void logout(){

    }

}
