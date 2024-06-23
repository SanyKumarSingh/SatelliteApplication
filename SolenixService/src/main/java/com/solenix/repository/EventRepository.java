/**
 * 
 */
package com.solenix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solenix.model.Satellite;
import com.solenix.model.Event;

/**
 * @author solenix
 *
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findBySatellite(Satellite satellite);
	
	
}
