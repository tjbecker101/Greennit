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
     * Creates a new thread in a subgreennit and pushes it to the database.
     * @param host_subgreennit  The subgreennit where the thread lies.
     * @param author            The user who created the thread.
     * @param content           The content of the thread.
     * @param creation_date     The time the thread was created.
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

    /**
     * Gets a thread existing in the database and returns it.
     * @param host_subgreennit  The subgreennit where the thread lies.
     * @param thread_id         The ID of the thread.
     * @return                  A thread from the database. May have no content.
     */
    public Thread getThread(int host_subgreennit, int thread_id) throws IllegalArgumentException {
        // Opens a new SQL session.
        session = sessionFactory.openSession();

        // Gets the thread class from the database.
        Thread thread = session.get(Thread.class, new Thread(host_subgreennit, thread_id));

        if (thread.getContent() == null || thread.getContent() == "") {
            throw new IllegalArgumentException("Host Subgreennit or Thread ID provided not valid.");
        }

        // Closes the session and return the thread.
        session.close();
        return thread;
    }

    /**
     * Updates a thread's content in the database.
     * @param host_subgreennit  The subgreennit where the thread lies.
     * @param thread_id         The ID of the thread.
     * @param content           The new content for the thread.
     */
    public void updateThreadContent(int host_subgreennit, int thread_id, String content) {
        // Get the thread from the database.
        Thread thread = getThread(host_subgreennit, thread_id);

        // Update the content of the thread.
        thread.setContent(content);

        // Begin a new SQL transaction.
        session = sessionFactory.openSession();
        session.beginTransaction();

        // Update the entry in the database.
        session.update(thread);

        // Commit the transaction and close the session.
        session.getTransaction().commit();
        session.close();

    }

    /**
     * Deletes a thread from the database.
     * @param host_subgreennit  The subgreennit where the thread lies.
     * @param thread_id         The ID of the thread.
     */
    public void deleteThread(int host_subgreennit, int thread_id) {
        // Get the thread from the database.
        Thread thread = getThread(host_subgreennit, thread_id);

        // Begin a new SQL transaction.
        session = sessionFactory.openSession();
        session.beginTransaction();

        // Delete the thread from the database.
        session.delete(thread);

        // Commit the transaction and close the session.
        session.getTransaction().commit();
        session.close();
    }

}
