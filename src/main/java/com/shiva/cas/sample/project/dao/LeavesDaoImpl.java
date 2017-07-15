package com.shiva.cas.sample.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.shiva.cas.sample.project.dao.rowmappers.LeavesRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.shiva.cas.sample.project.models.LeaveInfo;

public class LeavesDaoImpl implements LeavesDao {

	private JdbcTemplate jdbcTemplate;

	public LeavesDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getLeavesCount(String name) {
		try {
			return jdbcTemplate.queryForObject(GET_LEAVE_COUNT_BY_EMPLOYEE_NAME, new Object[] { name }, Integer.class);
		} catch (Exception e) {

		}
		return 0;
	}

	public List<LeaveInfo> getLeavesInfo(String name) {
		try {
			return jdbcTemplate.query(GET_LEAVES_BY_EMPLOYEE_NAME, new Object[] { name }, new LeavesRowMapper());
		} catch (Exception e) {

		}
		return new LinkedList<LeaveInfo>();
	}

	public int createLeave(final Long employeeId, final LeaveInfo leaveInfo) {
		if (leaveInfo == null) {
			throw new IllegalArgumentException("leaveInfo cannot be null");
		}

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(CREATE_LEAVE, new String[] { "leave_id" });
				ps.setString(1, leaveInfo.getDescription());
				ps.setInt(2, leaveInfo.getNumberOfDays());
				ps.setLong(3, leaveInfo.getFromDate());
				ps.setLong(4, leaveInfo.getToDate());
				ps.setString(5, leaveInfo.getLeaveType());
				ps.setLong(6, employeeId);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public LeaveInfo updateLeave(Long leaveId, LeaveInfo leaveInfo) {
		if (leaveInfo == null) {

		}
		try {
			jdbcTemplate.update(UPDATE_LEAVE, new Object[] { leaveInfo.getDescription(), leaveInfo.getNumberOfDays(),
					leaveInfo.getFromDate(), leaveInfo.getToDate(), leaveInfo.getLeaveType(), leaveId });
		} catch (Exception e) {

		}
		return leaveInfo;

	}

	public boolean deleteLeave(Long leaveId) {
		if (jdbcTemplate.update(DELETE_LEAVE_BY_ID, new Object[] { leaveId }) == 1) {
			return true;
		}
		return false;
	}

}
