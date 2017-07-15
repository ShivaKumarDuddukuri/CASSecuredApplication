package com.shiva.cas.sample.project.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shiva.cas.sample.project.models.LeaveInfo;

public class LeavesRowMapper implements RowMapper<LeaveInfo> {

	public LeaveInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		LeaveInfo leaveInfo = new LeaveInfo();
		leaveInfo.setLeaveId(rs.getInt("leave_id"));
		leaveInfo.setDescription(rs.getString("description"));
		leaveInfo.setNumberOfDays(rs.getInt("numberofdays"));
		leaveInfo.setFromDate(rs.getLong("fromdate"));
		leaveInfo.setToDate(rs.getLong("todate"));
		leaveInfo.setLeaveType(rs.getString("leaveType"));
		return leaveInfo;
	}
}