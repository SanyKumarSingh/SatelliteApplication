package com.solenix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author solenix
 *
 */
@Table(name="TLE_Information")
@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with 1 parameter for each field in your class
@Builder
public class TLEInformation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="Tle_information_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="Satellite_Id")
	private Satellite satellite;
	
    @Column(name="last_Update_Date")
    private Date lastUpdateDate;
    
    @Column(name="line1")
	private String line1;

	@Column(name="line2")
	private String line2;
}
