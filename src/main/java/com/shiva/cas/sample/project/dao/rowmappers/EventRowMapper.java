package com.shiva.cas.sample.project.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shiva.cas.sample.project.models.Event;

public class EventRowMapper implements RowMapper<Event> {

	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getInt("event_id"));
		event.setName(rs.getString("event_name"));
		event.setDescription(rs.getString("description"));
		event.setSummary(rs.getString("summary"));
		event.setEventDate(rs.getLong("eventdate"));
		return event;
	}
}