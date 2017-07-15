package com.shiva.cas.sample.project.spring.config;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.shiva.cas.sample.project.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.shiva.cas.sample.project.dao.EmployeeDaoImpl;
import com.shiva.cas.sample.project.dao.EventsDao;
import com.shiva.cas.sample.project.dao.EventsDaoImpl;
import com.shiva.cas.sample.project.dao.LeavesDao;
import com.shiva.cas.sample.project.dao.LeavesDaoImpl;

@Configuration
@Import(value = { SecurityConfig.class, JDBCDaoSpringConfig.class })
@ComponentScan(basePackages = {
		"com.shiva.cas.sample.project" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
				"com.shiva.cas.sample.project.controllers", "com.shiva.cas.sample.project.spring.web.config" }))

public class RootContextConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		List<Resource> resources = new LinkedList<Resource>();
		resources.add(new ClassPathResource("cas_app.properties"));
		configurer.setLocations(resources.toArray(new Resource[0]));
		configurer.setIgnoreUnresolvablePlaceholders(true);
		Environment environment = new StandardEnvironment();
		configurer.setEnvironment(environment);
		return configurer;
	}

	@Bean
	@Qualifier("employeeDao")
	public EmployeeDao employeeDao() {
		EmployeeDao employeeDao = new EmployeeDaoImpl(dataSource);
		return employeeDao;
	}

	@Bean
	@Qualifier("eventsDao")
	public EventsDao eventsDao() {
		EventsDao eventsDao = new EventsDaoImpl(dataSource);
		return eventsDao;
	}

	@Bean
	@Qualifier("leavesDao")
	public LeavesDao leavesDao() {
		LeavesDao leavesDao = new LeavesDaoImpl(dataSource);
		return leavesDao;
	}
}
