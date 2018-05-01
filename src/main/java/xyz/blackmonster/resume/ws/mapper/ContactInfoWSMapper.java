package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

/**
 * Mapper for transforming to and from ContactInfoWS
 */
public class ContactInfoWSMapper {

	public static ContactInfoWS toWS(ContactInfo contactInfo) {
		return new ContactInfoWS(
			contactInfo.getUuid(), 
			contactInfo.getEmail(), 
			contactInfo.getPhone(), 
			contactInfo.getStreet(), 
			contactInfo.getCity(), 
			contactInfo.getPostalCode(), 
			contactInfo.getCountry());
	}

	public static ContactInfo toModel(ContactInfoWS contactInfoWS) {
		return new ContactInfo(
			contactInfoWS.getUuid(),
			contactInfoWS.getEmail(),
			contactInfoWS.getPhone(),
			contactInfoWS.getStreet(),
			contactInfoWS.getCity(),
			contactInfoWS.getPostalCode(),
			contactInfoWS.getCountry());
	}
}
