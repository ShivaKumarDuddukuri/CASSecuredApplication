package com.shiva.cas.sample.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.cas.sample.project.dao.EmployeeDao;
import com.shiva.cas.sample.project.models.Employee;

@Service
public class EmployeesSearchServiceImpl implements EmployeesListingService {

	@Autowired
	private EmployeeDao employeeDao;

	public List<Employee> listAll() {
		return employeeDao.listAll();
	}

}
