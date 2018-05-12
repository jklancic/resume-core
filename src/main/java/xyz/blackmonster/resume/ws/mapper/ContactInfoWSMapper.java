package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

/**
 * Mapper for transforming to and from ContactInfoWS
 */
public class ContactInfoWSMapper {

	public static ContactInfoWS toWS(ContactInfo contactInfo) {
		return new ContactInfoWS(contactInfo);
	}

	public static ContactInfo toModel(ContactInfoWS contactInfoWS) {
		return new ContactInfoBuilder()
			.uuid(contactInfoWS.getUuid())
			.email(contactInfoWS.getEmail())
			.phone(contactInfoWS.getPhone())
			.street(contactInfoWS.getStreet())
			.city(contactInfoWS.getCity())
			.postalCode(contactInfoWS.getPostalCode())
			.country(contactInfoWS.getCountry())
			.build();
	}
}
