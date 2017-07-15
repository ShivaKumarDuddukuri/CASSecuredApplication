package com.shiva.cas.sample.project.spring.config;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.googlecode.flyway.core.Flyway;

@Configuration
@EnableTransactionManagement
public class JDBCDaoSpringConfig {

	private static final String DS_JNDI_NAME = "employees_ds";
	private static final String DB_SCRIPTS_LOCATION = "db_scripts/postgresql//";
	private static final String SQL_MIGRATION_PREFIX = "employees_v";
	private static final String SCHEMA = "employees";

	@Bean(destroyMethod = "")
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
		dataSource.setJndiName(DS_JNDI_NAME);
		dataSource.afterPropertiesSet();
		return (DataSource) dataSource.getObject();
	}

	@DependsOn("dbMigrator")
	@Bean
	public DataSourceTransactionManager gatewayTransactionManager() throws IllegalArgumentException, NamingException {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Bean
	public Flyway dbMigrator() throws IllegalArgumentException, NamingException {

		Flyway flyway = new Flyway();
		flyway.setInitOnMigrate(true);
		flyway.setPlaceholderPrefix("#{");
		flyway.setPlaceholderSuffix("}");
		flyway.setDataSource(dataSource());
		flyway.setLocations(DB_SCRIPTS_LOCATION);
		flyway.setSqlMigrationPrefix(SQL_MIGRATION_PREFIX);
		List<String> schemaNamesList = new LinkedList<String>();
		schemaNamesList.add(SCHEMA);
		flyway.setSchemas(listToStringArray(schemaNamesList));
		return flyway;
	}

	private String[] listToStringArray(List<String> listObjects) {
		if (listObjects != null && !listObjects.isEmpty()) {
			String[] strArry = new String[listObjects.size()];
			int i = 0;
			for (String s : listObjects) {
				strArry[i++] = s;
			}
			return strArry;
		}
		return null;
	}

}
