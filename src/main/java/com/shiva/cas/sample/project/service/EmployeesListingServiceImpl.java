package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.cas.sample.project.models.Employee;

@Service
public class EmployeesListingServiceImpl implements EmployeesSerachService {

	@Autowired private EmployeeDao employeeDao;

	public List<Employee> search(String key) {
		return employeeDao.search(key);
	}

}
