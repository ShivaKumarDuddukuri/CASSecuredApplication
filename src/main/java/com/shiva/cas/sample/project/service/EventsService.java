package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.models.Event;

public interface EventsService {

	List<Event> getEvents();

	Event getEventDetails(Long eventId);

	Event createEvent(Long employeeId, Event event);

	Event updateEvent(Long eventId, Event event);

	boolean deleteEvent(Long eventId);

}
