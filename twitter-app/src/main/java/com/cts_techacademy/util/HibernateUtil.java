package com.cts_techacademy.util;

import com.cts_techacademy.entity.Tweets;
import com.cts_techacademy.entity.Users;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static ServiceRegistry serviceRegistry;

	public static SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {
			try {
				InputStream inputStream = HibernateUtil.class.getResourceAsStream("/db.properties");
				Properties props = new Properties();
				props.load(inputStream);

				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();

				settings.put(Environment.DRIVER, props.getProperty("database.driver"));
				settings.put(Environment.URL, props.getProperty("database.url"));
				settings.put(Environment.USER, props.getProperty("database.user"));
				settings.put(Environment.PASS, props.getProperty("database.password"));
				settings.put(Environment.DIALECT, props.getProperty("hibernate.dialect"));
				settings.put(Environment.SHOW_SQL, props.getProperty("hibernate.show_sql"));
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, props.getProperty("hibernate.CURRENT_SESSION_CONTEXT_CLASS"));
				settings.put(Environment.HBM2DDL_AUTO, props.getProperty("hibernate.hbm2ddl.auto"));

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Users.class);

				configuration.addAnnotatedClass(Tweets.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static void close() throws Exception{
		if(serviceRegistry!= null) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}
	}
}
