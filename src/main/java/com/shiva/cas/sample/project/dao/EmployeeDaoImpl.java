package com.shiva.cas.sample.project.dao;

import java.util.List;

import javax.sql.DataSource;

import com.shiva.cas.sample.project.models.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shiva.cas.sample.project.dao.rowmappers.EmployeeRowMapper;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public EmployeeDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Employee getDetails(Long employeeId) {
		return jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID, new Object[] { employeeId }, new EmployeeRowMapper());
	}

	public Employee updateDetails(String employeeName, Employee employeeDetails) {

		if (employeeDetails == null) {

		}
		try {
			jdbcTemplate.update(UPDATE_EMPLOYEE,
					new Object[] { employeeDetails.getUsername(), employeeDetails.getPassword(),
							employeeDetails.getFirstName(), employeeDetails.getLastName(), employeeDetails.getEmail(),
							employeeDetails.getAddress(), employeeDetails.getDesignation(),
							employeeDetails.getDepartment(), employeeDetails.getPhoneNumber(),
							employeeDetails.getBloodGroup(), employeeName });
		} catch (Exception e) {

		}
		return employeeDetails;
	}

	public Employee getDetails(String name) {
		return jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_NAME, new Object[] { name }, new EmployeeRowMapper());
	}

	public int getEmployeeIdByName(String name) {
		return jdbcTemplate.queryForObject(GET_EMPLOYEE_ID_BY_NAME, new Object[] { name }, Integer.class);
	}

	public List<Employee> listAll() {
		return jdbcTemplate.query(GET_ALL_EMPLOYEES, new EmployeeRowMapper());
	}

	public List<Employee> search(String key) {
		return jdbcTemplate.query(getSearchQuery(SEARCH_EMPLOYEES_BY_NAME, key), new EmployeeRowMapper());
	}

	public String getSearchQuery(String baseQuery, String searchTerm) {
		return baseQuery.replace("{}", searchTerm);
	}
}
