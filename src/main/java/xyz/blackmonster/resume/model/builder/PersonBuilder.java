package xyz.blackmonster.resume.model.builder;

import java.sql.Date;

import xyz.blackmonster.resume.model.Person;

/**
 * Person builder
 */
public class PersonBuilder implements Builder<Person> {

	private Person person;

	public PersonBuilder() {
		person = new Person();
	}

	public PersonBuilder uuid(String uuid) {
		person.setUuid(uuid);
		return this;
	}

	public PersonBuilder birthDate(Date birthDate) {
		person.setBirthDate(birthDate);
		return this;
	}

	public PersonBuilder firstName(String firstName) {
		person.setFirstName(firstName);
		return this;
	}

	public PersonBuilder lastName(String lastName) {
		person.setLastName(lastName);
		return this;
	}

	public PersonBuilder overview(String overview) {
		person.setOverview(overview);
		return this;
	}

	public PersonBuilder contactInfoUuid(String contactInfoUuid) {
		person.setContactInfoUuid(contactInfoUuid);
		return this;
	}

	public PersonBuilder baseUrl(String baseUrl) {
		person.setBaseUrl(baseUrl);
		return this;
	}

	public PersonBuilder linkedInUrl(String linkedInUrl) {
		person.setLinkedInUrl(linkedInUrl);
		return this;
	}

	public PersonBuilder githubUrl(String githubUrl) {
		person.setGithubUrl(githubUrl);
		return this;
	}

	public PersonBuilder facebookUrl(String facebookUrl) {
		person.setFacebookUrl(facebookUrl);
		return this;
	}

	public PersonBuilder twitterUrl(String twitterUrl) {
		person.setTwitterUrl(twitterUrl);
		return this;
	}

	public PersonBuilder createdByUser(String createdByUser) {
		person.setCreatedByUser(createdByUser);
		return this;
	}

	@Override
	public Person build() {
		return person;
	}
}
