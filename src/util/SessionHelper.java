package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionHelper {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistryBuilder serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.buildServiceRegistry());
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
