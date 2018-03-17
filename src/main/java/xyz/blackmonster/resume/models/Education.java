package xyz.blackmonster.resume.models;

import java.sql.Date;

/**
 * Education
 */
public class Education {
	
	private String uuid;
	private Date date;
	private String title;
	private String institution;
	private String city;
	private String country;
	private String personUuid;
	
	public Education() {
		
	}

	public Education(String uuid, Date date, String title, String institution, String city, String country, String personUuid) {
		this.uuid = uuid;
		this.date = date;
		this.title = title;
		this.institution = institution;
		this.city = city;
		this.country = country;
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

	public String getPersonUuid() {
		return personUuid;
	}

	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}
}
