package com.greennit.CS3141;

/**
 * This user class allows one to create posts, edit or delete existing posts, as well as create a new subgreenit.
 * The user can also logout, change password, or delete their account with this class
 */
public class User{

    DatabaseConnection database = new DatabaseConnection();

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
        String data = database.query("select karma from users where username = " + username + ";", true, "karma");
        return Integer.parseInt(data);
    }

    private void createPost(Post post){

    }

    private void editPost(Post post){

    }

    private void deletePost(Post post) {

    }

    private void createSubgreenit(String name){

    }

    /**
     * Changes the users password
     * @param password - the new password they want to change it to
     * @return - Simple string saying it is complete
     */
    private String changePassword(String password){
        database.query("update users set password = " + password + " where username = " + username + ";", false, null);
        return "Password Change Complete";
    }

    /**
     * Performs a query to delete a user from the database
     */
    private void deleteAccount(){
        logout();
        database.query("delete from users where username = " + username + ";", false, null);
    }

    private void logout(){

    }

}
