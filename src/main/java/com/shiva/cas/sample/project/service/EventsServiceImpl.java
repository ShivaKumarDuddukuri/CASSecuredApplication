package com.shiva.cas.sample.project.service;

import java.util.List;

import com.shiva.cas.sample.project.dao.EventsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.cas.sample.project.models.Event;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired private EventsDao eventsDao;

	public List<Event> getEvents() {
		return eventsDao.getEvents();
	}

	public Event getEventDetails(Long eventId) {
		return eventsDao.getEventDetails(eventId);
	}

	public Event createEvent(Long employeeId, Event event) {
		event.setId(eventsDao.createEvent(employeeId, event));
		return event;
	}

	public Event updateEvent(Long eventId, Event event) {
		return eventsDao.updateEvent(eventId, event);
	}

	public boolean deleteEvent(Long eventId) {
		return eventsDao.deleteEvent(eventId);
	}

}
