package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

/**
 * Web Service response object for Experience
 */
public class ExperienceWS {

	private String uuid;
	private Date startDate;
	private Date endDate;
	private String title;
	private String description;
	private String city;
	private String country;
	
	public ExperienceWS() {
		
	}

	public ExperienceWS(String uuid, Date startDate, Date endDate, String title, String description, String city, String country) {
		this.uuid = uuid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.description = description;
		this.city = city;
		this.country = country;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}