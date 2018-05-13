package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.Person;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.model.builder.PersonBuilder;
import xyz.blackmonster.resume.ws.response.PersonWS;

public class PersonWSMapperTest {

	@Test
	public void testToWS() {
		Person person = new PersonBuilder()
			.uuid("uuid")
			.birthDate(new Date(System.currentTimeMillis()))
			.firstName("firstName")
			.lastName("lastName")
			.overview("overview")
			.baseUrl("baseUrl")
			.linkedInUrl("linkedInUrl")
			.githubUrl("githubUrl")
			.facebookUrl("facebookUrl")
			.twitterUrl("twitterUrl")
			.build();

		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid("uuid")
			.email("email")
			.phone("phone")
			.street("street")
			.city("city")
			.postalCode("postalCode")
			.country("country")
			.build();

		PersonWS personWS = PersonWSMapper.toWS(person, contactInfo);
		validate(person, personWS);

		assertThat(personWS.getContactInfo().getUuid()).isEqualTo(contactInfo.getUuid());
		assertThat(personWS.getContactInfo().getEmail()).isEqualTo(contactInfo.getEmail());
		assertThat(personWS.getContactInfo().getPhone()).isEqualTo(contactInfo.getPhone());
		assertThat(personWS.getContactInfo().getStreet()).isEqualTo(contactInfo.getStreet());
		assertThat(personWS.getContactInfo().getCity()).isEqualTo(contactInfo.getCity());
		assertThat(personWS.getContactInfo().getPostalCode()).isEqualTo(contactInfo.getPostalCode());
		assertThat(personWS.getContactInfo().getCountry()).isEqualTo(contactInfo.getCountry());
	}

	@Test
	public void testToModel() {
		PersonWS personWS = new PersonWS();
		personWS.setUuid("uuid");
		personWS.setBirthDate(new Date(System.currentTimeMillis()));
		personWS.setFirstName("firstName");
		personWS.setLastName("lastName");
		personWS.setOverview("overview");
		personWS.setBaseUrl("baseUrl");
		personWS.setLinkedInUrl("linkedInUrl");
		personWS.setGithubUrl("githubUrl");
		personWS.setFacebookUrl("facebookUrl");
		personWS.setTwitterUrl("twitterUrl");

		String createByUser = "createdByUser";
		Person person = PersonWSMapper.toModel(personWS, createByUser);
		validate(person, personWS);
	}

	private void validate(Person person, PersonWS personWS) {
		assertThat(personWS.getUuid()).isEqualTo(person.getUuid());
		assertThat(personWS.getBirthDate()).isEqualTo(person.getBirthDate());
		assertThat(personWS.getFirstName()).isEqualTo(person.getFirstName());
		assertThat(personWS.getLastName()).isEqualTo(person.getLastName());
		assertThat(personWS.getOverview()).isEqualTo(person.getOverview());
		assertThat(personWS.getBaseUrl()).isEqualTo(person.getBaseUrl());
		assertThat(personWS.getLinkedInUrl()).isEqualTo(person.getLinkedInUrl());
		assertThat(personWS.getGithubUrl()).isEqualTo(person.getGithubUrl());
		assertThat(personWS.getFacebookUrl()).isEqualTo(person.getFacebookUrl());
		assertThat(personWS.getTwitterUrl()).isEqualTo(person.getTwitterUrl());
	}
}
