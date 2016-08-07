package com.giong.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.giong.web.persistence" })
@EnableJpaRepositories(basePackages = { "com.giong.web.repository" })
@PropertySource("classpath:application.properties")
public class PersistenceContext {

	private final String MODEL_PACKAGES_TO_SCAN = "com.giong.web.persistence";
	private final String DRIVER_CLASS_NAME = "driverClassName";
	private final String URL = "url";
	private final String USERNAME = "user";
	private final String PASSWORD = "pass";
	private final String HIBERNATE_DIALECT = "hibernate.dialect";
	private final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private final String HIBERNATE_ORDER_UPDATES = "hibernate.order_updates";
	private final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

	@Resource
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.env.getProperty(this.DRIVER_CLASS_NAME));
		dataSource.setUrl(this.env.getProperty(this.URL));
		dataSource.setUsername(this.env.getProperty(this.USERNAME));
		dataSource.setPassword(this.env.getProperty(this.PASSWORD));
		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactory =
			new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(this.dataSource());
		entityManagerFactory.setPackagesToScan(this.MODEL_PACKAGES_TO_SCAN);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(this.hibernateProperties());
		entityManagerFactory.afterPropertiesSet();
		return entityManagerFactory;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties hibernateProperties() {
		final Properties prop = new Properties();
		prop.put(this.HIBERNATE_DIALECT, this.env.getProperty(this.HIBERNATE_DIALECT));
		prop.put(this.HIBERNATE_SHOW_SQL, this.env.getProperty(this.HIBERNATE_SHOW_SQL));
		prop.put(this.HIBERNATE_ORDER_UPDATES, this.env.getProperty(this.HIBERNATE_ORDER_UPDATES));
		prop.put(this.HIBERNATE_USE_SQL_COMMENTS, this.env.getProperty(this.HIBERNATE_USE_SQL_COMMENTS));
		prop.put(this.HIBERNATE_HBM2DDL_AUTO, this.env.getProperty(this.HIBERNATE_HBM2DDL_AUTO));
		return prop;
	}
}
