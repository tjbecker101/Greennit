package com.greennit.CS3141;

import org.junit.*;

import static org.junit.Assert.assertEquals;


public class Test_User {

    User user;

    @Before
    public void start() {

    }

    //Simple test of creating a user
    @Test
    public void checkForUsername() {
        assertEquals("tjbecker", user.getUsername());
    }
}
