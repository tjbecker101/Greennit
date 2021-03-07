package com.greennit.CS3141;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserManager {

    private SessionFactory sessionFactory;
    private Session session;

    /**
     * Creates a connection with the database using hibernate
     */
    public void setup() {
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
     * @param email - the email of the new user
     */
    public void create(String username, String password, String email) {
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
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * This will search the database for a user from their username
     * @param username - used as the primary key in the table and helps us locate the entry in the table
     * @return - returns a user which we can then use to get the information we need
     * @throws IllegalArgumentException - thrown if the user isn't in the table
     */
    public User getUser(String username) throws IllegalArgumentException {

        session = sessionFactory.openSession();
        User user = session.get(User.class, username);

        if (user.getUsername() == null || user.getUsername().equals("")) { //Checks if the username exists or not
            throw new IllegalArgumentException("Username provided not valid.");
        }

        session.close();
        return user;
    }

    /**
     * Used to update a value already in the table, such as changing username, or password, or updating their karma
     */
    public void update() {

    }

    /**
     * Will remove a user from the table and as a result delete their account
     * @param username - the username of the one to be deleted
     */
    public void delete(String username) {

        User user = new User();
        user.setUsername(username);
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }
}
