package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;
import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Mapper for transforming to and from PersonWS
 */
public class PersonWSMapper {

	public static PersonWS toWS(Person person, ContactInfo contactInfo) {
		ContactInfoWS contactInfoWS = ContactInfoWSMapper.toWS(contactInfo);
		return new PersonWS(
			person.getUuid(),
			person.getBirthDate(),
			person.getFirstName(),
			person.getLastName(),
			person.getOverview(),
			contactInfoWS);
	}

	public static Person toModel(PersonWS personWS, String baseUrl, String createdByUser) {
		return new Person(
			personWS.getUuid(),
			personWS.getBirthDate(),
			personWS.getFirstName(),
			personWS.getLastName(),
			personWS.getOverview(),
			personWS.getContactInfo().getUuid(),
			baseUrl,
			createdByUser);
	}
}
