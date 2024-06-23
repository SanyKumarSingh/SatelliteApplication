package com.solenix.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solenix
 *
 */
@Table(name="Satellite")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="Satellite_Id") 
	private Long id;
	
	@Column(name="name")
	private String satelliteName;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="satellite", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Event> events;

	@OneToOne(fetch=FetchType.LAZY, mappedBy="satellite", cascade = CascadeType.ALL, orphanRemoval=true)
	private TLEInformation tleInformation;
}
