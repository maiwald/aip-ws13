package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import entities.Bankkonto;
import entities.Kinokarte;
import entities.Kinosaal;
import entities.Kunde;


public class HibernateUtil
{
	private static SessionFactory sessionfactory;
	private static ServiceRegistry serviceRegistry;

 
    static
    {
        try
        {
        	Configuration configuration = new Configuration();
        	configuration.addAnnotatedClass(Kunde.class);
        	configuration.addAnnotatedClass(Kinokarte.class);
        	configuration.addAnnotatedClass(Kinosaal.class);
        	configuration.addAnnotatedClass(Bankkonto.class);
        	configuration.configure();
        	serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
        	sessionfactory = configuration.buildSessionFactory(serviceRegistry);


                             
        }
        catch (RuntimeException ex)
        {
            System.out.println(ex.getMessage());            
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionfactory;
    }
}