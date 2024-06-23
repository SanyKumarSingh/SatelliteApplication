/**
 * 
 */
package com.solenix.repository;

import com.solenix.model.Event;
import com.solenix.model.Satellite;
import com.solenix.model.TLEInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author solenix
 *
 */
@Repository
public interface TLEInformationRepository extends JpaRepository<TLEInformation, Long> {

    TLEInformation findBySatellite(Satellite satellite);

}
