package xyz.blackmonster.resume.models;

import java.sql.Date;

/**
 * Achievement like award, certificate, etc
 */
public class Achievement {
	
	private String uuid;
	private Date date;
	private String description;
	private String userUuid;
	
	public Achievement() {
		
	}
	
	public Achievement(String uuid, Date date, String description, String userUuid) {
		this.uuid = uuid;
		this.date = date;
		this.description = description;
		this.userUuid = userUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
}
