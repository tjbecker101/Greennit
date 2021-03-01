package com.greennit.CS3141;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 * This class manages the input, reading, deletion, and updating of entities
 * into the thread database.
 */
public class ThreadManager {

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

    public void create() {

    }

    public void read() {

    }

    public void update() {

    }

    public void delete() {

    }

}
