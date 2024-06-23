/**
 * 
 */
package com.solenix.repository;

import com.solenix.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solenix.model.Satellite;

import java.util.List;

/**
 * @author solenix
 *
 */
@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    //custom jpa method to fetch satellite data by Name
    Satellite findBySatelliteName(String satelliteName);
}
