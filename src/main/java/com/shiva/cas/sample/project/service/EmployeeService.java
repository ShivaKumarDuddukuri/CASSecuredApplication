package com.shiva.cas.sample.project.service;

import com.shiva.cas.sample.project.models.Employee;

public interface EmployeeService {

	Employee getDetails();

	Employee updateDetails(Employee employeeDetails);

	Employee getDetails(String name);

}
