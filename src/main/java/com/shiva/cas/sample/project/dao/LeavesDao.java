package com.shiva.cas.sample.project.dao;

import java.util.List;

import com.shiva.cas.sample.project.models.LeaveInfo;

public interface LeavesDao {

	static final String GET_LEAVE_BY_ID = "select * from leaves where leave_id = ? ";

	static final String GET_LEAVES_BY_EMPLOYEE_NAME = "select leaves.leave_id ,leaves.description,leaves.numberofdays , leaves.fromdate ,leaves.todate , leaves.leaveType from leaves INNER JOIN employees ON leaves.id_employee = employees.id_employee where employees.username  = ? ";

	static final String GET_LEAVE_COUNT_BY_EMPLOYEE_NAME = "select count(*) from leaves INNER JOIN employees ON leaves.id_employee = employees.id_employee where employees.username = ? ";

	static final String DELETE_LEAVE_BY_ID = "delete  from leaves where leave_id = ? ";

	static final String CREATE_LEAVE = "insert into leaves (description, numberofdays, fromdate, todate,leaveType, id_employee ) values (?, ?, ?, ? ,?, ?) ";

	static final String UPDATE_LEAVE = "update leaves set description = ? , numberofdays = ? , fromdate=  ? , todate= ? , leaveType = ? where  leave_id = ? ";

	int getLeavesCount(String name);

	List<LeaveInfo> getLeavesInfo(String name);

	int createLeave(Long employeeId, LeaveInfo leaveInfo);

	LeaveInfo updateLeave(Long leaveId, LeaveInfo leaveInfo);

	boolean deleteLeave(Long leaveId);

}
