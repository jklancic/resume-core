package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

/**
 * Web Service response object for Person
 */
public class PersonWS {

	private String uuid;
	private Date birthDate;
	private String firstName;
	private String lastName;
	private String overview;
	private ContactInfoWS contactInfo;
	
	public PersonWS() {
		
	}

	public PersonWS(String uuid, Date birthDate, String firstName, String lastName, String overview, ContactInfoWS contactInfo) {
		this.uuid = uuid;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.overview = overview;
		this.contactInfo = contactInfo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public ContactInfoWS getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfoWS contactInfo) {
		this.contactInfo = contactInfo;
	}
}
