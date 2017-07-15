package com.shiva.cas.sample.project.service;

import com.shiva.cas.sample.project.dao.EmployeeDao;
import com.shiva.cas.sample.project.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired private EmployeeDao employeeDao;

	public Employee getDetails() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return getDetails(name);
	}

	public Employee updateDetails(Employee employeeDetails) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return employeeDao.updateDetails(name, employeeDetails);
	}

	public Employee getDetails(String name) {
		return employeeDao.getDetails(name);
	}
}
