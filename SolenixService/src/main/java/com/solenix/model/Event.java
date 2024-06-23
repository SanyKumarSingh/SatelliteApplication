package com.solenix.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solenix
 *
 */
@Table(name="Event")
@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with 1 parameter for each field in your class
@Builder
public class Event  {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="Event_id") 
	private Long id;
	
	@ManyToOne  
	@JoinColumn(name="Satellite_Id")
	private Satellite satellite;
	
    @Column(name="event_date")
    private Date date;
    
    @Column(name="description")
	private String description;
    
	@Column(name="priority")
	private String priority;
}
