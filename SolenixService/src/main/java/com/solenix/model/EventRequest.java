/**
 * 
 */
package com.solenix.model;

/**
 * @author solenix
 *
 */
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Event_Request")
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event_Request_Id")
    private Long id;
    
    @ManyToOne  
	@JoinColumn(name="user_id")
	private User user;

    @Lob
    @Column(name = "JSON_REQUEST")
    private String json;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
	private Date date;

    @Column(name = "STATUS", length = 50)
    private String status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

