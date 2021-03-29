package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.entities.Subgreennit;
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
 * into the subgreennit database.
 */
public class SubgreennitManager {

    private SessionFactory sessionFactory;
    private Session session;

    public SubgreennitManager() {
        setup();
    }

    /**
     * Initializes the necessary components needed for
     * the sessionFactory to operate.
     */
    private void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
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


    public void updateName(int id, String name){
        session = sessionFactory.openSession();
        session.beginTransaction();

        Subgreennit subgreennit = session.get(Subgreennit.class, id);
        subgreennit.setName(name);
        session.update(subgreennit);

        session.getTransaction().commit();
        session.close();
    }

    public void updateDescription(int id, String description){
        session = sessionFactory.openSession();
        session.beginTransaction();

        Subgreennit subgreennit = session.get(Subgreennit.class, id);
        subgreennit.setDescription(description);
        session.update(subgreennit);

        session.getTransaction().commit();
        session.close();
    }

    public void createSubgreennit(String name, String description) {
        Subgreennit subgreennit = new Subgreennit();
        subgreennit.setName(name);
        subgreennit.setDescription(description);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(subgreennit);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Gets a subgreennit existing in the database and returns it.
     * @param id         The ID of the subgreennit.
     * @return           A subgreennit from the database. May have no content.
     */
    public Subgreennit getSubgreennit(int id) throws IllegalArgumentException {
        session = sessionFactory.openSession();

        Subgreennit subgreennit = session.get(Subgreennit.class, id);

        if (subgreennit == null) {
            throw new IllegalArgumentException("Subgreennit ID provided not valid.");
        }

        session.close();
        return subgreennit;
    }

    /**
     * Prototype method
     * Gets a list of subgreennits given a filter.
     * @param filter    The filter to filter through the database with.
     * @return          A list of subgreennits matching the filter.
     */
    public List<Subgreennit> getSubgreennits(String filter) {
        try {
            session = sessionFactory.openSession();

            String hql = "from Subgreennit where " + filter;
            Query<Subgreennit> query = session.createQuery(hql);

            return query.list();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }


    /**
     * Updates a subgreennit's content in the database.
     * @param id         The ID of the subgreennit.
     * @param description    The new description for the subgreennit.
     */
    public void updateSubgreennitContent(int id, String description) {
        Subgreennit subgreennit = getSubgreennit(id);

        subgreennit.setDescription(description);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(subgreennit);

        session.getTransaction().commit();
        session.close();

    }

    /**
     * Deletes a subgreennit from the database.
     * @param id    The ID of the subgreennit.
     */
    public void deleteSubgreennit(int id) {
        Subgreennit subgreennit = getSubgreennit(id);

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(subgreennit);

        session.getTransaction().commit();
        session.close();
    }

}
