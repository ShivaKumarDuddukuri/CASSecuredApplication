package com.shiva.cas.sample.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.cas.sample.project.models.Event;
import com.shiva.cas.sample.project.service.EventsService;

@RestController
@RequestMapping("/events")
public class EmployeeEventsController {

	@Autowired
	private EventsService eventsService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Event> getEvents() {
		return eventsService.getEvents();
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
	public Event getEventDetails(@PathVariable(value = "eventId") Long eventId) {
		return eventsService.getEventDetails(eventId);
	}

}