package com.shiva.cas.sample.project.spring.config;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.header.writers.HstsHeaderWriter;

import com.shiva.cas.sample.project.cas.user.details.CasUserPrincipalService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${com.shiva.cas.sample.project.cas.service.url}")
	private String serviceUrl;

	@Value("${com.shiva.cas.sample.project.cas.ticket.validation.url}")
	private String validationUrl;

	@Value("${com.shiva.cas.sample.project.cas.login.url}")
	private String loginUrl;

	@Bean
	public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
		SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		return simpleUrlAuthenticationFailureHandler;
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		savedRequestAwareAuthenticationSuccessHandler.setDefaultTargetUrl("/");
		return savedRequestAwareAuthenticationSuccessHandler;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(serviceUrl);
		return serviceProperties;
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		Cas20ServiceTicketValidator validator = new Cas20ServiceTicketValidator(validationUrl);
		return validator;
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setProxyReceptorUrl("/j_spring_cas_security_proxyreceptor");
		casAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		casAuthenticationFilter.setServiceProperties(serviceProperties());
		casAuthenticationFilter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler());
		casAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(loginUrl);
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("test");
		return casAuthenticationProvider;
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilter(casAuthenticationFilter()).authorizeRequests().anyRequest().authenticated()
				.and().formLogin().usernameParameter("j_username").passwordParameter("j_password").and()
				.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint());

		http.headers().contentTypeOptions().xssProtection().cacheControl().frameOptions()
				.addHeaderWriter(new HstsHeaderWriter(60 * 60, false));
	}

	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService() {
		return new CasUserPrincipalService();
	}

}
