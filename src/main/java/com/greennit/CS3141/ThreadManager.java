package com.greennit.CS3141;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;

/*
 * This class manages the input, reading, deletion, and updating of entities
 * into the thread database.
 */
public class ThreadManager {

    private SessionFactory sessionFactory;
    private Session session;

    public ThreadManager() {
        setup();
    }

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

    /**
     *
     * @param host_subgreennit
     * @param author
     * @param content
     * @param creation_date
     */
    public void createThread(int host_subgreennit, String author, String content, Timestamp creation_date) {
        // Create a new thread
        Thread thread = new Thread();
        //thread.setHost_subgreennit(host_subgreennit);
        thread.setAuthor(author);
        thread.setContent(content);
        thread.setCreation_date(creation_date);

        // Begin a new SQL transaction
        session = sessionFactory.openSession();
        session.beginTransaction();

        // Push the thread to the database transaction
        session.save(thread);

        // Commit the transaction and close the session.
        session.getTransaction().commit();
        session.close();
    }

    public Thread getThread(int host_subgreennit, int thread_id) {
        session = sessionFactory.openSession();

        Thread thread = session.get(Thread.class, new Thread(host_subgreennit, thread_id));

        session.close();
        return thread;
    }

    public void updateThreadContent(int host_subgreennit, int thread_id, String content) {
        Thread thread = getThread(host_subgreennit, thread_id);
        thread.setContent(content);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(thread);

        session.getTransaction().commit();
        session.close();

    }

    public void deleteThread(int host_subgreennit, int thread_id) {
        Thread thread = getThread(host_subgreennit, thread_id);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(thread);

        session.getTransaction().commit();
        session.close();
    }

}
