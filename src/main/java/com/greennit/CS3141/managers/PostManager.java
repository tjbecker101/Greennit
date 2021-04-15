package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

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


    public Post createPost(int host_thread, String author, String content) {
        Post post = new Post();
        post.setAuthor(author);
        post.setContent(content);
        post.setHost_thread(host_thread);
        post.setLikes(0);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setTime_posted(timestamp);


        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(post);

        session.getTransaction().commit();
        session.close();

        return post;
    }

    /**
     * Gets a post existing in the database and returns it.
     * @param id    The ID of the post.
     * @return      A post from the database. May have no content.
     */
    public Post getPost(int id) throws IllegalArgumentException {

        session = sessionFactory.openSession();
        Post post = session.get(Post.class, id);

        if (post == null || post.getContent().equals("")) {
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
    public void updatePostContent(int id, String content) {
        Post post = getPost(id);

        post.setContent(content);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(post);

        session.getTransaction().commit();
        session.close();

    }

    /**
     * Prototype method
     * Gets a list of threads given a host thread.
     * @param host_thread   The thread where all the posts are located at.
     * @return              A list of posts in the thread.
     */
    public List<Post> getPostsByThread(int host_thread) {
        try {
            session = sessionFactory.openSession();

            String hql = "from Post where host_thread = :thread";
            Query<Post> query = session.createQuery(hql);
            query.setParameter("thread", host_thread);

            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Deletes a thread from the database.
     * @param id    The ID of the post.
     */
    public Post deletePost(int id) {
        Post post = getPost(id);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(post);

        session.getTransaction().commit();
        session.close();

        return post;
    }

}
