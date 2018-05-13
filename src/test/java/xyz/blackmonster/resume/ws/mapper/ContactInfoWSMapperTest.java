package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

public class ContactInfoWSMapperTest {

	@Test
	public void testToWS() {
		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid("uuid")
			.email("email")
			.phone("phone")
			.street("street")
			.city("city")
			.postalCode("postalCode")
			.country("country")
			.build();

		ContactInfoWS contactInfoWS = ContactInfoWSMapper.toWS(contactInfo);
		validate(contactInfo, contactInfoWS);
	}

	@Test
	public void testToModel() {
		ContactInfoWS contactInfoWS = new ContactInfoWS();
		contactInfoWS.setUuid("uuid");
		contactInfoWS.setEmail("email");
		contactInfoWS.setPhone("phone");
		contactInfoWS.setStreet("street");
		contactInfoWS.setCity("city");
		contactInfoWS.setPostalCode("postalCode");
		contactInfoWS.setCountry("country");

		ContactInfo contactInfo = ContactInfoWSMapper.toModel(contactInfoWS);
		validate(contactInfo, contactInfoWS);
	}

	private void validate(ContactInfo contactInfo, ContactInfoWS contactInfoWS) {
		assertThat(contactInfoWS.getUuid()).isEqualTo(contactInfo.getUuid());
		assertThat(contactInfoWS.getEmail()).isEqualTo(contactInfo.getEmail());
		assertThat(contactInfoWS.getPhone()).isEqualTo(contactInfo.getPhone());
		assertThat(contactInfoWS.getStreet()).isEqualTo(contactInfo.getStreet());
		assertThat(contactInfoWS.getCity()).isEqualTo(contactInfo.getCity());
		assertThat(contactInfoWS.getPostalCode()).isEqualTo(contactInfo.getPostalCode());
		assertThat(contactInfoWS.getCountry()).isEqualTo(contactInfo.getCountry());
	}
}
