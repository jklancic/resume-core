package xyz.blackmonster.resume.models;

import java.sql.Date;

/**
 * Person
 */
public class Person {
	
	private String uuid;
	private Date birthDate;
	private String firstName;
	private String lastName;
	private String overview;
	private String contactInfoUuid;
	private String createdByUser;
	
	public Person() {
		
	}

	public Person(String uuid, Date birthDate, String firstName, String lastName, String overview, String contactInfoUuid, String createdByUser) {
		this.uuid = uuid;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.overview = overview;
		this.contactInfoUuid = contactInfoUuid;
		this.createdByUser = createdByUser;
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

	public String getContactInfoUuid() {
		return contactInfoUuid;
	}

	public void setContactInfoUuid(String contactInfoUuid) {
		this.contactInfoUuid = contactInfoUuid;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
}
