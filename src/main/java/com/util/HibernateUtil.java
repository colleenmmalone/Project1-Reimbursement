package com.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.driver.MainDriver1;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 
    
    public static SessionFactory getSessionFactory() throws Exception{
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://javareactdb.cjbpnp0jr9da.us-east-1.rds.amazonaws.com:5432/reimburse");
                settings.put(Environment.USER, "tix");
                settings.put(Environment.PASS, "payme123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(com.model.LoginInfo.class);
                configuration.addAnnotatedClass(com.model.TicketsInfo.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }finally {
        	lg.info("Session is complete");
        }
        

    }
		return sessionFactory;
    } 
    
    
    public HibernateUtil() {
    	
    }
}