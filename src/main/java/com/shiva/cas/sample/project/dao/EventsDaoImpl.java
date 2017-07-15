package com.shiva.cas.sample.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.shiva.cas.sample.project.dao.rowmappers.EventRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.shiva.cas.sample.project.models.Event;

public class EventsDaoImpl implements EventsDao {

	private JdbcTemplate jdbcTemplate;

	public EventsDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Event> getEvents() {
		return jdbcTemplate.query(GET_ALL_EVENTS, new EventRowMapper());
	}

	public Event getEventDetails(Long eventId) {
		return jdbcTemplate.queryForObject(GET_EVENT_BY_EVENT_ID, new Object[] { eventId }, new EventRowMapper());
	}

	public int createEvent(final Long employeeId, final Event event) {
		if (event == null) {
			throw new IllegalArgumentException("event cannot be null");
		}

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(CREATE_EVENT, new String[] { "event_id" });
				ps.setString(1, event.getName());
				ps.setString(2, event.getDescription());
				ps.setString(3, event.getSummary());
				ps.setLong(4, event.getEventDate());
				ps.setLong(5, employeeId);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public Event updateEvent(Long eventId, Event event) {
		if (event == null) {

		}
		try {
			jdbcTemplate.update(UPDATE_EVENT, new Object[] { event.getName(), event.getDescription(),
					event.getSummary(), event.getEventDate(), eventId });
		} catch (Exception e) {

		}
		return event;
	}

	public boolean deleteEvent(Long eventId) {
		if (jdbcTemplate.update(DELETE_EVENT_BY_ID, new Object[] { eventId }) == 1) {
			return true;
		}
		return false;
	}

}
