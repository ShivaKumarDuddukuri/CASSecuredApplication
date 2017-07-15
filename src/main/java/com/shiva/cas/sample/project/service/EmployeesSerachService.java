package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.models.Employee;

public interface EmployeesSerachService {

	List<Employee> search(String key);

}
