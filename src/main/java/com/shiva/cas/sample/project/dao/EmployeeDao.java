package com.shiva.cas.sample.project.dao;

import java.util.List;

import com.shiva.cas.sample.project.models.Employee;

public interface EmployeeDao {

	static final String GET_EMPLOYEE_BY_ID = "select * from employees where id_employee = ? ";

	static final String GET_EMPLOYEE_BY_NAME = "select * from employees where username = ? ";

	static final String GET_EMPLOYEE_ID_BY_NAME = "select id_employee from employees where username = ? ";

	static final String UPDATE_EMPLOYEE = " update employees set username = ? , password = ? , first_name = ? , last_name= ? , email= ? , address= ? , designation= ? ,department = ?  phoneNumber = ? , bloodGroup = ?   where username = ? ";

	static final String GET_ALL_EMPLOYEES = "select * from employees ";

	static final String SEARCH_EMPLOYEES_BY_NAME = "select * from employees  where first_name ILIKE '%{}%' or last_name ILIKE '%{}%' or  email ILIKE '%{}%' or  address ILIKE '%{}%' or  designation ILIKE '%{}%' or  department ILIKE '%{}%' or  phoneNumber ILIKE '%{}%'";

	List<Employee> search(String key);

	Employee getDetails(Long employeeId);

	Employee updateDetails(String name, Employee employeeDetails);

	Employee getDetails(String name);

	int getEmployeeIdByName(String name);

	List<Employee> listAll();

}
