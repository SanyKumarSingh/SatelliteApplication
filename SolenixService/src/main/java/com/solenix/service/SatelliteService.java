/**
 * 
 */
package com.solenix.service;

import com.solenix.dto.EventDTO;
import com.solenix.dto.SatelliteDTO;
import com.solenix.exception.DataAccessException;

import java.util.List;

/**
 * @author solenix
 *
 */
public interface SatelliteService {

	public List<SatelliteDTO> fetchSatellites() throws DataAccessException;
	
}
