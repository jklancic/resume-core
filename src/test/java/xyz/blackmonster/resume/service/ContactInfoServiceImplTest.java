package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.repository.dao.ContactInfoDAO;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

@RunWith(MockitoJUnitRunner.class)
public class ContactInfoServiceImplTest {

	@Mock
	private ContactInfoDAO contactInfoDAO;

	private ContactInfoService contactInfoService;

	@Before
	public void setUp() {
		contactInfoService = new ContactInfoServiceImpl(contactInfoDAO);
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid(uuid)
			.email("email")
			.build();
		when(contactInfoDAO.getByUuid(eq(uuid))).thenReturn(Optional.of(contactInfo));

		ContactInfoWS contactInfoWS = contactInfoService.getByUuid(uuid);
		validate(contactInfoWS, contactInfo);
	}

	@Test
	public void testGetByPersonUuid() {
		String uuid = "uuid";
		String personUuid = "personUuid";
		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid(uuid)
			.email("email")
			.build();
		when(contactInfoDAO.getByPersonUuid(eq(personUuid))).thenReturn(Optional.of(contactInfo));

		ContactInfoWS contactInfoWS = contactInfoService.getByPersonUuid(personUuid);
		validate(contactInfoWS, contactInfo);
	}

	@Test
	public void testUpdate() {
		ArgumentCaptor<ContactInfo> captor = ArgumentCaptor.forClass(ContactInfo.class);
		String uuid = "uuid";
		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid(uuid)
			.email("email")
			.build();
		ContactInfoWS contactInfoWS = new ContactInfoWS();
		contactInfoWS.setUuid(uuid);
		contactInfoWS.setEmail("email");
		when(contactInfoDAO.getByUuid(eq(uuid))).thenReturn(Optional.of(contactInfo));

		ContactInfoWS created = contactInfoService.update(contactInfoWS);
		verify(contactInfoDAO, times(1)).update(captor.capture());
		assertThat(captor.getValue()).isNotNull();
		assertThat(captor.getValue().getUuid()).isNotBlank();
		assertThat(captor.getValue().getEmail()).isEqualTo(contactInfo.getEmail());
		verify(contactInfoDAO, times(1)).getByUuid(eq(uuid));
		validate(created, contactInfo);
	}

	private void validate(ContactInfoWS contactInfoWS, ContactInfo contactInfo) {
		assertThat(contactInfoWS.getUuid()).isEqualTo(contactInfo.getUuid());
		assertThat(contactInfoWS.getEmail()).isEqualTo(contactInfo.getEmail());
	}
}
