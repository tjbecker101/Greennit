package com.greennit.CS3141;

import org.junit.*;
import static org.junit.Assert.assertEquals;


public class Test_User {

    //User (tjbecker, 1, 123@idk.com, test, 2) will already be in database

    UserManager manager;
    User user;
    String username;

    @Before
    public void start() {
        manager = new UserManager();
        username = "tjbecker";
    }

    //Tests the getters
    @Test
    public void checkForUsername() {
        user = manager.getUser(username);
        assertEquals("tjbecker", user.getUsername());
    }

    @Test
    public void checkForEmail(){
        user = manager.getUser(username);
        assertEquals("123@idk.com", user.getEmail());
    }

    @Test
    public void checkforKarma(){
        user = manager.getUser(username);
        assertEquals(2, user.getKarma());
    }

    @Test
    public void checkForPermission(){
        user = manager.getUser(username);
        assertEquals(1, user.getPermission());
    }

    @Test
    public void checkForPassword(){
        user = manager.getUser(username);
        assertEquals("test", user.getPass());
    }

    @After
    public void finish(){
        manager.exit();
    }

    /*Tests putting new values into the database
    @Test
    public void checkInsertingUsername(){
        user = new User("test1","test1", "test1@idk.com");
        assertEquals("test1", user.getUsername());
    }

    //Testing the setters
    @Test
    public void editPassword(){
        user.changePassword("test2");
        assertEquals("test2", user.getPass());
    }

    @Test
    public void editEmail(){
        user.setEmail("test2@idk.com");
        assertEquals("test2@idk.com", user.getPass());
    }

    @Test
    public void editKarma(){
        user.setKarma(100);
        assertEquals(100, user.getKarma());
    }

    @Test
    public void editUsername() {
        user.setUsername("rename");
        assertEquals("rename", user.getUsername());
    }

    @Test
    public void editPermission(){
        user.setPermission(2);
        assertEquals(2, user.getKarma());
    }

     */

}
