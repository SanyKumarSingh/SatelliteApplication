/**
 * 
 */
package com.solenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solenix.model.EventRequest;

/**
 * @author solenix
 *
 */
@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {
	
}
