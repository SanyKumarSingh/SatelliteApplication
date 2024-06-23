/**
 * 
 */
package com.solenix.service.impl;

import com.solenix.dto.EventDTO;
import com.solenix.model.Event;
import com.solenix.model.Satellite;
import com.solenix.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solenix.exception.DataAccessException;
import com.solenix.repository.SatelliteRepository;
import com.solenix.service.EventService;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solenix
 */
@Service
public class EventServiceImpl implements EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	
	@Autowired
	private SatelliteRepository satelliteRepository;

	@Autowired
	private EventRepository eventRepository;

	/**
	 * Lot Split
	 */
	@Override
	@Transactional
	public EventDTO createEvent(EventDTO eventDTO) throws DataAccessException {
		logger.info("Processing LotSplit Request ####");
		Satellite satellite = satelliteRepository.findBySatelliteName(eventDTO.getSatelliteName());
		Event event;
		if(satellite != null) {
			event = Event.builder()
					.date(eventDTO.getDate())
					.description(eventDTO.getDescription())
					.priority(eventDTO.getPriority())
					.satellite(satellite)
					.build();

			eventRepository.save(event);
		} else {
			logger.info("No such Satellite exists !!!!");
			return null;
		}
		// Return the detailed response.
		logger.info("Event create successfully !!!!");
		return EventDTO.builder()
				.date(event.getDate())
				.description(event.getDescription())
				.priority(event.getPriority())
				.satelliteName(event.getSatellite().getSatelliteName())
				.build();
	}

	@Override
	@Transactional
	public List<EventDTO> fetchEvents() throws DataAccessException {
			List<Event> eventList = eventRepository.findAll();
			return eventList.stream().map(this::mapToProductresponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<EventDTO> fetchEvents(String satelliteName) throws DataAccessException {
		Satellite satellite = satelliteRepository.findBySatelliteName(satelliteName);
		List<Event> eventList = eventRepository.findBySatellite(satellite);
		return eventList.stream().map(this::mapToProductresponse).collect(Collectors.toList());
	}

	private EventDTO mapToProductresponse(Event event) {
		return EventDTO.builder()
				.date(event.getDate())
				.description(event.getDescription())
				.priority(event.getPriority())
				.satelliteName(event.getSatellite().getSatelliteName())
				.build();

	}
}
