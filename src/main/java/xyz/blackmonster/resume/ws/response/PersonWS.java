package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.Person;

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
	private String baseUrl;
	private String linkedInUrl;
	private String githubUrl;
	private String facebookUrl;
	private String twitterUrl;
	
	public PersonWS() {
		
	}

	public PersonWS(Person person, ContactInfo contactInfo) {
		this.uuid = person.getUuid();
		this.birthDate = person.getBirthDate();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.overview = person.getOverview();
		if (contactInfo != null) {
			this.contactInfo = new ContactInfoWS(contactInfo);
		}
		this.baseUrl = person.getBaseUrl();
		this.linkedInUrl = person.getLinkedInUrl();
		this.githubUrl = person.getGithubUrl();
		this.facebookUrl = person.getFacebookUrl();
		this.twitterUrl = person.getTwitterUrl();
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

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
}
