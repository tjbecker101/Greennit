package com.greennit.CS3141;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserManager {

    private SessionFactory sessionFactory;
    private Session session;

    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void exit() {
        sessionFactory.close();
    }

    public void create(String username, String password, String email, int karma, int permission) {
        User user = new User();
        user.setUsername(username);
        user.setPermission(permission);
        user.setKarma(karma);
        user.setEmail(email);
        user.setPass(password);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(user);

        session.getTransaction().commit();
        session.close();
    }

    public User read() {
        Session session = sessionFactory.openSession();

        String username = "tjbecker";
        User user = session.get(User.class,username);
        session.close();
        return user;
    }

    public void update() {

    }

    public void delete(String username) {
        User user = new User();
        user.setUsername(username);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(user);

        session.getTransaction().commit();
        session.close();

    }
}
