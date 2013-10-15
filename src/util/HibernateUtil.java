package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import entities.Bankkonto;
import entities.Kunde;


public class HibernateUtil
{
	private static SessionFactory sessionfactory;
	private static ServiceRegistry serviceRegistry;

 
    static
    {
        try
        {
        	Configuration configuration = new Configuration().addAnnotatedClass(Kunde.class).addAnnotatedClass(Bankkonto.class);
        	
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