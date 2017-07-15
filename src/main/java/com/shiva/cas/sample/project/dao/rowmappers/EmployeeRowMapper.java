package com.shiva.cas.sample.project.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shiva.cas.sample.project.models.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id_employee"));
		employee.setUsername(rs.getString("username"));
		employee.setFirstName(rs.getString("first_name"));
		employee.setLastName(rs.getString("last_name"));
		employee.setEmail(rs.getString("email"));
		employee.setAddress(rs.getString("address"));
		employee.setDesignation(rs.getString("designation"));
		employee.setDepartment(rs.getString("department"));
		employee.setPhoneNumber(rs.getString("phoneNumber"));
		employee.setBloodGroup(rs.getString("bloodGroup"));

		return employee;
	}
}