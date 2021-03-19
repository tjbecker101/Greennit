package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserManager {

    private SessionFactory sessionFactory;
    private Session session;

    public UserManager() {
        setup();
    }

    /**
     * Creates a connection with the database using hibernate
     */
    private void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.print("\n" + e.getMessage() + "\n");
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Closes the open connection to the database
     */
    public void exit() {
        sessionFactory.close();
    }

    /**
     * Used to create a new entry in the database
     * @param username - the username of the new user
     * @param password - the password of the new user
     * @param email    - the email of the new user
     */
    public void createUser(String username, String password, String email) {
        //Creates a user with the provided values
        User user = new User();
        user.setUsername(username);
        user.setPermission(1);
        user.setKarma(0);
        user.setEmail(email);
        user.setPass(password);

        //Takes the user we created and puts it into the database as an entry
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * This will search the database for a user from their username
     *
     * @param username - used as the primary key in the table and helps us locate the entry in the table
     * @return - returns a user which we can then use to get the information we need
     * @throws IllegalArgumentException - thrown if the user isn't in the table
     */
    public User getUser(String username) throws IllegalArgumentException {

        session = sessionFactory.openSession();
        User user = session.get(User.class, username);

        if (user == null || user.getUsername().equals("")) { //Checks if the username exists or not
            throw new IllegalArgumentException("Username provided not valid.");
        }

        session.close();
        return user;
    }

    /**
     * Updates the given user in the database.
     * @param user  The user that needs to be updated.
     */
    private void updateUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(user);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Updates the username of a given user.
     * If oldUsername and newUsername are equal, or if user of newUsername exists, throw IllegalArgumentException.
     * @param oldUsername                   The original username of the user.
     * @param newUsername                   The new username of the user.
     * @throws IllegalArgumentException     If oldUsername = newUsername or if user of newUsername exists.
     */
    public void updateUsername(String oldUsername, String newUsername) throws IllegalArgumentException {
        User user = getUser(oldUsername);

        // New username is same as old username.
        if (newUsername.equals(oldUsername)) {
            throw new IllegalArgumentException("Old username and new username cannot be the same.");
        }

        // User already exists.
        try {
            if (getUser(newUsername).getUsername().equals(newUsername)) {
                throw new IllegalArgumentException("New username already exists in table.");
            }
        } catch(IllegalArgumentException ex){
            System.out.println("Username is valid");
        }

        //Deletes the users old data from the database
        deleteUser(oldUsername);
        //Takes the saved user info and changes the username to the new one
        user.setUsername(newUsername);

        //Commits the user back into the database with the new username
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();

    }

    /**
     * Updates the password of a given user.
     * @param username  The username to change the password of.
     * @param password  The new password to be changed to.
     */
    public void updatePassword(String username, String password) {
        User user = getUser(username);

        user.setPass(password);

        updateUser(user);
    }

    /**
     * Updates the email of a given user.
     * @param username  The username to change the email of.
     * @param email     The new email to be changed to.
     */
    public void updateEmail(String username, String email) {
        User user = getUser(username);

        user.setEmail(email);

        updateUser(user);
    }

    /**
     * Updates the permission level of a given user.
     * @param username      The username to change the permission of.
     * @param permission    The new permission to be changed to.
     */
    public void updatePermission(String username, int permission) {
        User user = getUser(username);

        user.setPermission(permission);

        updateUser(user);
    }

    /**
     * Updates the karma of a given user.
     * @param username  The username to change the karma of.
     * @param karma     The new karma to be changed to.
     */
    public void updateKarma(String username, int karma) {
        User user = getUser(username);

        user.setKarma(karma);

        updateUser(user);
    }


    /**
     * Will remove a user from the table and as a result delete their account
     *
     * @param username - the username of the one to be deleted
     */
    public void deleteUser(String username) {

        User user = new User();
        user.setUsername(username);
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }
}
