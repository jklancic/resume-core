package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

import xyz.blackmonster.resume.models.Experience;

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

	public ExperienceWS(Experience experience) {
		this.uuid = experience.getUuid();
		this.startDate = experience.getStartDate();
		this.endDate = experience.getEndDate();
		this.title = experience.getTitle();
		this.description = experience.getDescription();
		this.city = experience.getCity();
		this.country = experience.getCountry();
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
