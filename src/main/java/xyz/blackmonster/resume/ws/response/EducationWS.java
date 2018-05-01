package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

/**
 * Web Service response object for Education
 */
public class EducationWS {

	private String uuid;
	private Date date;
	private String title;
	private String institution;
	private String city;
	private String country;
	
	public EducationWS() {
		
	}

	public EducationWS(String uuid, Date date, String title, String institution, String city, String country) {
		this.uuid = uuid;
		this.date = date;
		this.title = title;
		this.institution = institution;
		this.city = city;
		this.country = country;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
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
