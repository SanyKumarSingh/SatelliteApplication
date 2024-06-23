/**
 * 
 */
package com.solenix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author solenix
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1774566647L;

	private Date date;
	private String description;
	private String priority;
	private String satelliteName;

}
