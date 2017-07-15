package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.models.LeaveInfo;

public interface LeavesService {

	int getLeavesCount();

	List<LeaveInfo> getLeavesInfo();

	LeaveInfo createLeave(LeaveInfo leaveInfo);

	LeaveInfo updateLeave(Long leaveId, LeaveInfo leaveInfo);

	boolean deleteLeave(Long leaveId);

}
