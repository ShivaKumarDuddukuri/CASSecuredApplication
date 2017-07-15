
package com.shiva.cas.sample.project.context.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContextEvent.getServletContext());
		ContextUtil.setContext(applicationContext);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
