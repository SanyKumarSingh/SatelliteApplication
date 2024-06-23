/**
 * 
 */
package com.solenix.utility;

import java.time.LocalDateTime;

import com.solenix.service.TLEService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author solenix
 *
 */
@Component
public class TLEInfoUpdateScheduler {
	
private static final Logger logger = LoggerFactory.getLogger(TLEInfoUpdateScheduler.class);

	@Autowired
	private TLEService tleService;
    
    /**
	 * Job to run every day at an interval of 2hours LocalTimeZone of the server
	 * to fetch TLE Information of the satellites from Celstark.
	 * 
	 * cron = "Seconds Minutes Hours DayOfTheMonth Month DayOfTheWeek"
	 * fixedRate specifies the interval in milliseconds between the end of the previous execution 
	 * and the start of the next execution. Here we have 2 hours in milliseconds.
	 */
    @Scheduled(fixedRate = 2 * 60 * 60 * 1000)
    public void updateTLEInformation() {
			logger.info("Starting updateTLEInformation Job!!!! Timestamp : " + LocalDateTime.now());
	    	try {
				tleService.updateTLEInformation();
			} catch (Exception exception) {
				logger.error("There seems to be an issue while updateTLEInformation Job.", exception);
			}
	    	logger.info("Completed updateTLEInformation Job!!!! Timestamp : " + LocalDateTime.now());
    }
}
