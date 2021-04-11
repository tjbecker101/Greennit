package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.UserManager;

public class UserDAO {

    public User checkLogin (String username, String password) {
        UserManager manager = new UserManager();
        User user;
        try {
            user = manager.getUser(username);
            if (user.getPass().equals(password)) {
                return user;
            }
            else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        finally {
            manager.exit();
        }
    }
}
