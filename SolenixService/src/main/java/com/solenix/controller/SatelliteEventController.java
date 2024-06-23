/**
 * 
 */
package com.solenix.controller;

import com.solenix.dto.EventDTO;
import com.solenix.dto.SatelliteDTO;
import com.solenix.dto.SatelliteData;
import com.solenix.model.Satellite;
import com.solenix.repository.SatelliteRepository;
import com.solenix.service.SatelliteService;
import com.solenix.service.TLEService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solenix.exception.DataAccessException;
import com.solenix.exception.InternalServerException;
import com.solenix.service.EventService;

import java.util.List;

/**
 * @author solenix
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class SatelliteEventController {
	
	private static final Logger logger = LoggerFactory.getLogger(SatelliteEventController.class);
	
	@Autowired
	private EventService eventService;

	@Autowired
	private SatelliteService satelliteService;

	@Autowired
	private SatelliteRepository satelliteRepository;

	@Autowired
	private TLEService tleService;
	
	@GetMapping("/events/list")
	public ResponseEntity<Object> getEventList() throws DataAccessException {
		logger.info("Received request to fetch list of events.");
		List<EventDTO> eventDTOList =  eventService.fetchEvents();
		return ResponseEntity.ok().body(eventDTOList);
	}
	
	@GetMapping("/events/satellite/{name}")
	public ResponseEntity<Object> getEventBySatellite(@PathVariable(value = "name") String satelliteName) throws DataAccessException {
		logger.info("Received request to fetch list of events for satellite: " + satelliteName);
		List<EventDTO> eventDTOList =  eventService.fetchEvents(satelliteName);
		return ResponseEntity.ok().body(eventDTOList);
	}
	
	@PostMapping("/events/create")
	public ResponseEntity<Object> createEvent(@RequestBody EventDTO eventDTO) {
		if (eventDTO == null) {
			logger.error("Missing mandatory field.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing mandatory info");
		}

		Satellite satellite = satelliteRepository.findBySatelliteName(eventDTO.getSatelliteName());
		if(satellite == null) {
			logger.error("Missing satellite information.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing satellite information.");
		}
		logger.info("Received request to Create a new event.");

		EventDTO response = null;
		try {
			response = eventService.createEvent(eventDTO);
		}  catch (DataAccessException exception) {
			logger.error("There seems to be an issue while creating the Satellite.", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There seems to be an issue while creating the Satellite.");
		}
		
		// Return the response as a ResponseEntity with success status, message and JSON response.
		logger.info("Satellite creation completed successfully!!!");
		if (response != null ){
			return ResponseEntity.ok().body(response);
		}
		return ResponseEntity.badRequest().body("Data could not be saved, data already exist or no such satellite exists");
	}

	@GetMapping("/satellites/list")
	public ResponseEntity<Object> getSatellites() throws DataAccessException {
		logger.info("Received request to fetch list of satellites.");
		List<SatelliteDTO> satelliteList =  satelliteService.fetchSatellites();
		return ResponseEntity.ok().body(satelliteList);
	}

	@GetMapping("/tle/satellite/{name}")
	public ResponseEntity<Object> getTLEBySatellite(@PathVariable(value = "name") String satelliteName) throws DataAccessException {
		logger.info("Received request to fetch TLE Info for satellite: " + satelliteName);
		SatelliteData satelliteData = tleService.fetchTLE(satelliteName);
		if (satelliteData == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No TLE Information found for the Satellite: " + satelliteName);
		}
		return ResponseEntity.ok().body(satelliteData);
	}
}
