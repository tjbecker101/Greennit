package com.greennit.CS3141.managers;

import com.greennit.CS3141.entities.Subgreennit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 *
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


    public void updateTitle(int id, String name){
        session = sessionFactory.openSession();
        session.beginTransaction();

        Subgreennit subgreennit = session.get(Subgreennit.class, id);
        subgreennit.setTitle(name);
        session.update(subgreennit);

        session.getTransaction().commit();
        session.close();
    }


}
