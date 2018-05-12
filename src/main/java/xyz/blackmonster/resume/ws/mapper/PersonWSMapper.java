package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.models.builder.PersonBuilder;
import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Mapper for transforming to and from PersonWS
 */
public class PersonWSMapper {

	public static PersonWS toWS(Person person, ContactInfo contactInfo) {
		return new PersonWS(person, contactInfo);
	}

	public static Person toModel(PersonWS personWS, String createdByUser) {
		return new PersonBuilder()
			.uuid(personWS.getUuid())
			.birthDate(personWS.getBirthDate())
			.firstName(personWS.getFirstName())
			.lastName(personWS.getLastName())
			.overview(personWS.getOverview())
			.contactInfoUuid(personWS.getContactInfo().getUuid())
			.baseUrl(personWS.getBaseUrl())
			.linkedInUrl(personWS.getLinkedInUrl())
			.githubUrl(personWS.getGithubUrl())
			.facebookUrl(personWS.getFacebookUrl())
			.twitterUrl(personWS.getTwitterUrl())
			.build();
	}
}
