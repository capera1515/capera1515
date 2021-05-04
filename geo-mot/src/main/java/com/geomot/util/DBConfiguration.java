/**
 * 
 */
package com.geomot.util;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author acapera
 *
 */
@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws Exception {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		try {
			sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
			sessionFactory.setHibernateProperties(hibernateProperties());
			sessionFactory.setDataSource(dataSource());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() throws Exception {
		final BasicDataSource dataSource = new BasicDataSource();
		try {

			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USERNAME);
			dataSource.setPassword(PASSWORD);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() throws Exception {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		try {
			transactionManager.setSessionFactory(sessionFactory().getObject());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", DIALECT);
		hibernateProperties.setProperty("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

		return hibernateProperties;
	}
}
