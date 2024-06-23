/**
 * 
 */
package com.solenix.service.impl;

import com.solenix.dto.SatelliteData;
import com.solenix.dto.SatelliteJSONData;
import com.solenix.exception.DataAccessException;
import com.solenix.model.Satellite;
import com.solenix.model.TLEInformation;
import com.solenix.repository.SatelliteRepository;
import com.solenix.repository.TLEInformationRepository;
import com.solenix.service.TLEService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @author solenix
 */
@Service
public class TLEServiceImpl implements TLEService {
	
	private static final Logger logger = LoggerFactory.getLogger(TLEServiceImpl.class);
	
	@Autowired
	private SatelliteRepository satelliteRepository;

	@Autowired
	private TLEInformationRepository tleInformationRepository;

	@Value("${celestrak.satellite.last30days.information}")
	private String apiUrl;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Lot Split
	 */
	@Override
	@Transactional
	public void updateTLEInformation() throws DataAccessException {
		logger.info("Started Satellite information Update Scheduler ####");

		List<SatelliteData> satelliteDatas = getSatelliteData();

		satelliteDatas.forEach( satelliteData -> {
			Satellite satellite = satelliteRepository.findBySatelliteName(satelliteData.getName());
			if (satellite == null) {
				satellite = new Satellite();
				satellite.setSatelliteName(satelliteData.getName());
				satellite = satelliteRepository.save(satellite);
			}

			TLEInformation tleInformation = tleInformationRepository.findBySatellite(satellite);
			if (tleInformation == null) {
				tleInformation = new TLEInformation();
				tleInformation.setSatellite(satellite);
			}
			tleInformation.setLine1(satelliteData.getLine1());
			tleInformation.setLine2(satelliteData.getLine2());
			tleInformation.setLastUpdateDate(Calendar.getInstance().getTime());
			tleInformation = tleInformationRepository.save(tleInformation);

		});

		logger.info("Completed Satellite information Update Scheduler  ####");
	}

	private List<SatelliteData> getSatelliteData() {
		String response = restTemplate.getForObject(apiUrl, String.class);
		return parseTleResponse(response);
	}

	private List<SatelliteData> parseTleResponse(String response) {
		List<SatelliteData> satelliteDataList = new ArrayList<>();

		String[] lines = response.split("\n");

		for (int i = 0; i < lines.length; i += 3) {
			if (i + 2 < lines.length) {
				SatelliteData satelliteData = new SatelliteData();
				satelliteData.setName(lines[i].trim());
				satelliteData.setLine1(lines[i + 1].trim());
				satelliteData.setLine2(lines[i + 2].trim());
				satelliteDataList.add(satelliteData);
			}
		}
		return satelliteDataList;
	}

	/**
	 * @param satelliteName
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public SatelliteData fetchTLE(String satelliteName) throws DataAccessException {
		SatelliteData satelliteData = null;
		Satellite satellite = satelliteRepository.findBySatelliteName(satelliteName);
		if (satellite == null) {
			return satelliteData;
		}

		TLEInformation tleInformation = tleInformationRepository.findBySatellite(satellite);
		if (tleInformation == null) {
			return satelliteData;
		}

		satelliteData = new SatelliteData();
		satelliteData.setName(tleInformation.getSatellite().getSatelliteName());
		satelliteData.setLine1(tleInformation.getLine1());
		satelliteData.setLine2(tleInformation.getLine2());

		return satelliteData;
	}


}
