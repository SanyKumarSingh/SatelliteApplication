/**
 * 
 */
package com.solenix.service;

import com.solenix.dto.EventDTO;
import com.solenix.exception.DataAccessException;

import java.util.List;

/**
 * @author solenix
 *
 */
public interface EventService {
	
	public EventDTO createEvent(EventDTO eventDTO) throws DataAccessException;

	public List<EventDTO> fetchEvents() throws DataAccessException;

	public List<EventDTO> fetchEvents(String satelliteName) throws DataAccessException;
	
}
