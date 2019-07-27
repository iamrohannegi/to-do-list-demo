package com.todoapp.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.todoapp.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class ToDoAppConfig implements WebMvcConfigurer{
	
	//Inject property file.
	@Autowired
	private Environment env;
	
	//Translates view-name to a URL to be rendered on browser							
	@Bean
	public ViewResolver viewResolver() {
	
		InternalResourceViewResolver internalViewResolver = new InternalResourceViewResolver();
		internalViewResolver.setPrefix("/WEB-INF/view/");
		internalViewResolver.setSuffix(".jsp");
		
		return internalViewResolver;
	}
	
	
	//Set-up the database as our DataSource using information provided in property file.
	@Bean
	public DataSource dataSource() {
		//Connection Pool
		ComboPooledDataSource theDataSource = new ComboPooledDataSource();
		
		//Setting Jdbc Driver
		try {
			theDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}
		catch(PropertyVetoException ex) {
			ex.printStackTrace();
		}
		
		//Setting Jdbc Properties
		theDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		theDataSource.setUser(env.getProperty("jdbc.username"));
		theDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//Setting Connection Pool Settings
		theDataSource.setInitialPoolSize(getIntegerProperty("connection.pool.initialPoolSize"));
		theDataSource.setMinPoolSize(getIntegerProperty("connection.pool.minPoolSize"));
		theDataSource.setMaxPoolSize(getIntegerProperty("connection.pool.maxPoolSize"));
		theDataSource.setMaxIdleTime(getIntegerProperty("connection.pool.maxIdleTime"));
		
		return theDataSource;
	}
	
	
	//To access static resources like css,etc.
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/")
                    .setCachePeriod(31556926);
    }
	
	//Convert String value to Integer from properties file	
	private int getIntegerProperty(String propertyName) {
		return Integer.parseInt(env.getProperty(propertyName));
	}
	
	//Create Session Factory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	
	//Get hibernate properties from properties file
	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		return props;
	}
	
	//Set-up hibernate transaction manager
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
