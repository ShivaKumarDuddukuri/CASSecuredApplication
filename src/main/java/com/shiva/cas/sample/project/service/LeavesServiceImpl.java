package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shiva.cas.sample.project.dao.LeavesDao;
import com.shiva.cas.sample.project.models.LeaveInfo;

@Service
public class LeavesServiceImpl implements LeavesService {

	@Autowired private LeavesDao leavesDao;

	@Autowired private EmployeeDao employeeDao;

	public int getLeavesCount() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return leavesDao.getLeavesCount(name);
	}

	public List<LeaveInfo> getLeavesInfo() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return leavesDao.getLeavesInfo(name);
	}

	public LeaveInfo createLeave(LeaveInfo leaveInfo) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		int employeeId = employeeDao.getEmployeeIdByName(name);
		int id = leavesDao.createLeave(Long.valueOf(employeeId), leaveInfo);
		leaveInfo.setLeaveId(id);
		return leaveInfo;
	}

	public LeaveInfo updateLeave(Long leaveId, LeaveInfo leaveInfo) {
		return leavesDao.updateLeave(leaveId, leaveInfo);
	}

	public boolean deleteLeave(Long leaveId) {
		return leavesDao.deleteLeave(leaveId);
	}

}
