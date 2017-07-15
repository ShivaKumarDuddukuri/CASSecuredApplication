package com.shiva.cas.sample.project.dao;

import java.util.List;

import com.shiva.cas.sample.project.models.Event;

public interface EventsDao {

	static final String GET_EVENT_BY_ID = "select * from events where event_id = ? ";

	static final String GET_ALL_EVENTS = "select * from events ";

	static final String GET_EVENT_BY_EMPLOYEE_ID = "select * from events where id_employee = ? ";

	static final String GET_EVENT_BY_EVENT_ID = "select * from events where event_id = ? ";

	static final String DELETE_EVENT_BY_ID = "delete  from events where event_id = ? ";

	static final String CREATE_EVENT = "insert into events ( name , description, summary, eventdate,id_employee) values ( ? , ?, ?, ?, ? ) ";

	static final String UPDATE_EVENT = "update events set name = ? , description = ? , summary = ? , eventdate = ? where event_id = ? ";

	List<Event> getEvents();

	Event getEventDetails(Long eventId);

	int createEvent(Long employeeId, Event event);

	Event updateEvent(Long eventId, Event event);

	boolean deleteEvent(Long eventId);
}
