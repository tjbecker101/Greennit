package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.Thread;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

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

    /**
     * Initializes the necessary components needed for
     * the sessionFactory to operate.
     */
    private void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Closes the sessionFactory and, as a result, any ThreadManager executions.
     */
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
    public Thread createThread(int host_subgreennit, String title, String author, String content, Timestamp creation_date) {
        try {
            Thread thread = new Thread();
            thread.setHost_subgreennit(host_subgreennit);
            thread.setTitle(title);
            thread.setAuthor(author);
            thread.setContent(content);
            thread.setCreation_date(creation_date);

            session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(thread);

            session.getTransaction().commit();
            session.close();

            return thread;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            return null;
        }

    }

    /**
     * Gets a thread existing in the database and returns it.
     * @param id         The ID of the thread.
     * @return                  A thread from the database. May have no content.
     */
    public Thread getThread(int id) throws IllegalArgumentException {
        session = sessionFactory.openSession();

        Thread thread = session.get(Thread.class, id);

        if (thread == null || thread.getContent().equals("")) {
            throw new IllegalArgumentException("Host Subgreennit or Thread ID provided not valid.");
        }

        session.close();
        return thread;
    }

    /**
     * Prototype method
     * Gets a list of threads given a host subgreennit.
     * @param host_subgreenit   The filter to filter through the database with.
     * @return                  A list of threads matching the host subgreennit.
     */
    public List<Thread> getThreadsBySubgreennit(int host_subgreenit) {
        try {
            session = sessionFactory.openSession();

            String hql = "from Thread where host_subgreennit = :condition";
            Query<Thread> query = session.createQuery(hql);
            query.setParameter("condition", host_subgreenit);

            return query.list();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Prototype method
     * Gets a list of threads given a host subgreennit.
     * @param host_subgreenit   The filter to filter through the database with.
     * @return                  A list of threads matching the host subgreennit.
     */
    public List<Thread> getThreadsByTitle(String title) {
        try {
            session = sessionFactory.openSession();

            String hql = "from Thread where title like %:condition%";
            Query<Thread> query = session.createQuery(hql);
            query.setParameter("condition", title);

            return query.list();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Updates a thread's content in the database.
     * @param id         The ID of the thread.
     * @param content           The new content for the thread.
     */
    public void updateThreadContent(int id, String content) {
        try {
            Thread thread = getThread(id);

            thread.setContent(content);

            session = sessionFactory.openSession();
            session.beginTransaction();

            session.update(thread);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
        }

    }

    /**
     * Deletes a thread from the database.
     * @param id    The ID of the thread.
     */
    public Thread deleteThread(int id) {
        try {
            Thread thread = getThread(id);

            session = sessionFactory.openSession();
            session.beginTransaction();

            session.delete(thread);

            session.getTransaction().commit();
            session.close();

            return thread;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            return null;
        }
    }

}
