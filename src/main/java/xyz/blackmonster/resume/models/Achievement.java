package xyz.blackmonster.resume.models;

import java.sql.Date;

/**
 * Achievement like award, certificate, etc
 */
public class Achievement {
	
	private String uuid;
	private Date date;
	private String description;
	private String personUuid;
	
	public Achievement() {
		
	}
	
	public Achievement(String uuid, Date date, String description, String personUuid) {
		this.uuid = uuid;
		this.date = date;
		this.description = description;
		this.personUuid = personUuid;
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

	public String getPersonUuid() {
		return personUuid;
	}

	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}
}
