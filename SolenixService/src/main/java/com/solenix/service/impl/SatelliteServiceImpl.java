/**
 * 
 */
package com.solenix.service.impl;

import com.solenix.dto.EventDTO;
import com.solenix.dto.SatelliteDTO;
import com.solenix.exception.DataAccessException;
import com.solenix.model.Event;
import com.solenix.model.Satellite;
import com.solenix.repository.EventRepository;
import com.solenix.repository.SatelliteRepository;
import com.solenix.service.EventService;
import com.solenix.service.SatelliteService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solenix
 */
@Service
public class SatelliteServiceImpl implements SatelliteService {
	
	private static final Logger logger = LoggerFactory.getLogger(SatelliteServiceImpl.class);
	
	@Autowired
	private SatelliteRepository satelliteRepository;

	/**
	 * Lot Split
	 */


	@Override
	@Transactional
	public List<SatelliteDTO> fetchSatellites() throws DataAccessException {
			List<Satellite> satellites = satelliteRepository.findAll();
			return satellites.stream().map(this::mapToSatelliteResponse).collect(Collectors.toList());
	}

	private SatelliteDTO mapToSatelliteResponse(Satellite satellite) {
		return SatelliteDTO.builder()
				.id(satellite.getId())
				.name(satellite.getSatelliteName())
				.build();

	}

}
