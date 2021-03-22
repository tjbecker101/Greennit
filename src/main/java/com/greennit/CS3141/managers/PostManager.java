package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.Post;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;

/*
 * This class manages the input, reading, deletion, and updating of entities
 * into the post database.
 */
public class PostManager {

    private SessionFactory sessionFactory;
    private Session session;

    public PostManager() {
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
     * Closes the sessionFactory and, as a result, any PostManager executions.
     */
    public void exit() {
        sessionFactory.close();
    }

    /**
     * Creates a new post in a thread and pushes it to the database.
     * @param host_thread       The thread where the post lies.
     * @param author            The user who created the post.
     * @param content           The content of the post.
     * @param creation_date     The time the post was created.
     */
    public void createThread(int host_thread, String author, String content, Timestamp creation_date) {
        Post post = new Post();
        post.setHost_thread(host_thread);
        post.setAuthor(author);
        post.setContent(content);
        post.setTime_posted(creation_date);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(post);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Gets a post existing in the database and returns it.
     * @param id    The ID of the post.
     * @return      A post from the database. May have no content.
     */
    public Post getPost(int id) throws IllegalArgumentException {

        session = sessionFactory.openSession();
        Post post = session.get(Post.class, id);

        if (post.getContent() == null || post.getContent().equals("")) {
            throw new IllegalArgumentException("Host Thread or Post ID provided not valid.");
        }

        session.close();
        return post;
    }

    /**
     * Updates a post's content in the database.
     * @param id        The ID of the post.
     * @param content   The new content for the post.
     */
    public void updateThreadContent(int id, String content) {
        Post post = getPost(id);

        post.setContent(content);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(post);

        session.getTransaction().commit();
        session.close();

    }

    /**
     * Deletes a thread from the database.
     * @param id    The ID of the post.
     */
    public void deletePost(int id) {
        Post post = getPost(id);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(post);

        session.getTransaction().commit();
        session.close();
    }

}
