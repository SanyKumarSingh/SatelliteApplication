/**
 * 
 */
package com.solenix.service;

import com.solenix.dto.EventDTO;
import com.solenix.dto.SatelliteData;
import com.solenix.exception.DataAccessException;

import java.util.List;

/**
 * @author solenix
 *
 */
public interface TLEService {
	
	public void updateTLEInformation() throws DataAccessException;

	public SatelliteData fetchTLE(String satelliteName) throws DataAccessException;

}
