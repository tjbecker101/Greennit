package com.greennit.CS3141;

import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class Test_UserManager {

    //User (tjbecker, 1, 123@idk.com, test, 2) will already be in database
    //Automated Tests are Done

    UserManager manager;
    User user;
    String username;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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

    @Test()
    public void createAndDeleteAccount(){
        manager.createUser("createTest", "createTestPass", "createTest@idk.com");
        user = manager.getUser("createTest");
        assertEquals("createTest", user.getUsername());
        manager.deleteUser("createTest");
        try{
            manager.getUser("createTest");
        }catch(IllegalArgumentException ex){
            assertEquals("Username provided not valid.", ex.getMessage());
        }
    }

    @Test
    public void updateUsername(){
        manager.createUser("oldUsername", "test", "idk@idk.com");
        manager.updateUsername("oldUsername", "newUsername");
        user = manager.getUser("newUsername");
        assertEquals("newUsername", user.getUsername());
        manager.deleteUser("newUsername");
    }

    @Test
    public void updatePassword(){
        manager.createUser("username", "oldPassword", "idk@idk.com");
        manager.updatePassword("username", "newPassword");
        user = manager.getUser("username");
        assertEquals("newPassword", user.getPass());
        manager.deleteUser("username");
    }

    @Test
    public void updateKarma(){
        manager.createUser("username", "oldPassword", "idk@idk.com");
        manager.updateKarma("username", 123);
        user = manager.getUser("username");
        assertEquals(123, user.getKarma());
        manager.deleteUser("username");
    }

    @Test
    public void updatePermission(){
        manager.createUser("username", "oldPassword", "idk@idk.com");
        manager.updatePermission("username", 123);
        user = manager.getUser("username");
        assertEquals(123, user.getPermission());
        manager.deleteUser("username");
    }

    @Test
    public void updateEmail(){
        manager.createUser("username", "oldPassword", "oldEmail@idk.com");
        manager.updateEmail("username", "newEmail@idk.com");
        user = manager.getUser("username");
        assertEquals("newEmail@idk.com", user.getEmail());
        manager.deleteUser("username");
    }

    /*
    Below tests the throwables in all the methods
     */
    @Test(expected = IllegalArgumentException.class)
    public void getUserErrors(){
        manager.getUser("invalidUsername");
    }

    @Test()
    public void updateUserErrors1(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Old username and new username cannot be the same.");
        manager.updateUsername("tjbecker","tjbecker");
    }

    @Test()
    public void updateUserErrors2(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("New username already exists in table.");
        manager.updateUsername("tjbecker","qcross");
    }

    @After
    public void finish(){
        manager.exit();
    }

}
