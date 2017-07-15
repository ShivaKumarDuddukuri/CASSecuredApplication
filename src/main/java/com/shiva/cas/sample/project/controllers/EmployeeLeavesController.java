package com.shiva.cas.sample.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.cas.sample.project.models.LeaveInfo;
import com.shiva.cas.sample.project.service.LeavesService;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeavesController {

	@Autowired
	private LeavesService leavesService;

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public int getLeavesCount() {
		return leavesService.getLeavesCount();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LeaveInfo> getLeavesInfo() {
		return leavesService.getLeavesInfo();
	}

	@RequestMapping(method = RequestMethod.POST)
	public LeaveInfo createLeave(@RequestBody LeaveInfo leaveInfo) {
		return leavesService.createLeave(leaveInfo);
	}

	@RequestMapping(value = "/{leaveId}", method = RequestMethod.PUT)
	public LeaveInfo updateLeave(@PathVariable(value = "leaveId") Long leaveId, @RequestBody LeaveInfo leaveInfo) {
		return leavesService.updateLeave(leaveId, leaveInfo);
	}

	@RequestMapping(value = "/{leaveId}", method = RequestMethod.DELETE)
	public boolean deleteLeave(@PathVariable(value = "leaveId") Long leaveId) {
		return leavesService.deleteLeave(leaveId);
	}

}