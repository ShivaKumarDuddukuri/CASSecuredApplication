package com.shiva.cas.sample.project.cas.user.details;

import com.shiva.cas.sample.project.context.utils.ContextUtil;
import com.shiva.cas.sample.project.service.EmployeeService;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasUserPrincipalService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		EmployeeService employeeService = ContextUtil.getSpringBean(EmployeeService.class);
		return employeeService.getDetails(token.getAssertion().getPrincipal().getName());
	}

}
