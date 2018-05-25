package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.Person;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.model.builder.PersonBuilder;
import xyz.blackmonster.resume.model.builder.UserBuilder;
import xyz.blackmonster.resume.repository.dao.AchievementDAO;
import xyz.blackmonster.resume.repository.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repository.dao.EducationDAO;
import xyz.blackmonster.resume.repository.dao.ExperienceDAO;
import xyz.blackmonster.resume.repository.dao.PersonDAO;
import xyz.blackmonster.resume.repository.dao.SkillDAO;
import xyz.blackmonster.resume.repository.dao.UserDAO;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;
import xyz.blackmonster.resume.ws.response.PersonWS;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

	@Mock
	private AchievementDAO achievementDAO;
	@Mock
	private ContactInfoDAO contactInfoDAO;
	@Mock
	private EducationDAO educationDAO;
	@Mock
	private ExperienceDAO experienceDAO;
	@Mock
	private PersonDAO personDAO;
	@Mock
	private SkillDAO skillDAO;
	@Mock
	private UserDAO userDAO;

	private PersonService personService;

	@Before
	public void setUp() {
		personService = new PersonServiceImpl(
			achievementDAO, contactInfoDAO, educationDAO, experienceDAO, personDAO, skillDAO, userDAO);
	}

	@Test
	public void testGetAll() {
		Person person = new PersonBuilder()
			.uuid("uuid")
			.birthDate(new Date(System.currentTimeMillis()))
			.firstName("firstname")
			.lastName("lastname")
			.overview("overview")
			.contactInfoUuid("contactInfoUuid")
			.baseUrl("baseUrl")
			.linkedInUrl("linkedIn")
			.githubUrl("github")
			.facebookUrl("facebook")
			.twitterUrl("twitter")
			.createdByUser("userUuid")
			.build();

		List<Person> personList = new ArrayList<>(1);
		personList.add(person);
		when(personDAO.getAll()).thenReturn(personList);

		List<PersonWS> personWSList = personService.getAll();
		assertThat(personWSList.size()).isEqualTo(personList.size());
		validate(personWSList.get(0), personList.get(0));
	}

	@Test
	public void testGetByUuid() {
		Person person = new PersonBuilder()
			.uuid("uuid")
			.birthDate(new Date(System.currentTimeMillis()))
			.firstName("firstname")
			.lastName("lastname")
			.overview("overview")
			.contactInfoUuid("contactInfoUuid")
			.baseUrl("baseUrl")
			.linkedInUrl("linkedIn")
			.githubUrl("github")
			.facebookUrl("facebook")
			.twitterUrl("twitter")
			.createdByUser("userUuid")
			.build();
		ContactInfo contactInfo = new ContactInfoBuilder()
			.uuid("contactInfoUuid")
			.email("email")
			.phone("phone")
			.street("street")
			.city("city")
			.postalCode("postalCode")
			.country("country")
			.build();

		when(personDAO.getByUuid(eq(person.getUuid()))).thenReturn(Optional.of(person));
		when(contactInfoDAO.getByUuid(eq(person.getContactInfoUuid()))).thenReturn(Optional.of(contactInfo));

		PersonWS personWS = personService.getByUuid(person.getUuid());
		validate(personWS, person);
	}

	@Test
	public void testGetUuid() {
		String uri = "https://resume-client.com";
		String uuid = "uuid";
		when(personDAO.getUuidByBaseUrl(eq(uri))).thenReturn(Optional.of(uuid));

		String personUuid = personService.getUuid(uri);
		assertThat(personUuid).isEqualTo(uuid);
	}

	@Test
	public void testCreate() {
		PersonWS personWS = new PersonWS();
		personWS.setBirthDate(new Date(System.currentTimeMillis()));
		personWS.setFirstName("firstName");
		personWS.setLastName("lastName");
		personWS.setOverview("overview");
		personWS.setBaseUrl("baseUrl");
		personWS.setLinkedInUrl("linkedInUrl");
		personWS.setGithubUrl("githubUrl");
		personWS.setFacebookUrl("facebookUrl");
		personWS.setTwitterUrl("twitterUrl");

		ContactInfoWS contactInfoWS = new ContactInfoWS();
		contactInfoWS.setEmail("email");
		contactInfoWS.setPhone("phone");
		contactInfoWS.setStreet("street");
		contactInfoWS.setCity("city");
		contactInfoWS.setPostalCode("postalCode");
		contactInfoWS.setCountry("country");
		personWS.setContactInfo(contactInfoWS);

		ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
		ArgumentCaptor<ContactInfo> contactCaptor = ArgumentCaptor.forClass(ContactInfo.class);
		String accessToken = "accessToken";
		String userUuid = "userUuid";
		User user = new UserBuilder()
			.uuid(userUuid)
			.build();
		when(userDAO.getByAccessToken(eq(accessToken))).thenReturn(Optional.of(user));
		Person person = new Person();
		person.setUuid("uuid");
		person.setContactInfoUuid("contactInfoUuid");
		when(personDAO.getByUuid(anyString())).thenReturn(Optional.of(person));
		when(contactInfoDAO.getByUuid(eq(person.getContactInfoUuid()))).thenReturn(Optional.of(new ContactInfo()));

		personService.create(personWS, accessToken);
		verify(contactInfoDAO, times(1)).create(contactCaptor.capture());
		assertThat(contactCaptor.getValue().getUuid()).isNotBlank();
		verify(personDAO, times(1)).create(personCaptor.capture());
		assertThat(contactCaptor.getValue().getUuid()).isNotBlank();
	}

	@Test
	public void testUpdateAll() {
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

		ContactInfoWS contactInfoWS = new ContactInfoWS();
		contactInfoWS.setUuid("contactUuid");
		contactInfoWS.setEmail("email");
		contactInfoWS.setPhone("phone");
		contactInfoWS.setStreet("street");
		contactInfoWS.setCity("city");
		contactInfoWS.setPostalCode("postalCode");
		contactInfoWS.setCountry("country");
		personWS.setContactInfo(contactInfoWS);

		String accessToken = "accessToken";
		String userUuid = "userUuid";
		User user = new UserBuilder()
			.uuid(userUuid)
			.build();
		when(userDAO.getByAccessToken(eq(accessToken))).thenReturn(Optional.of(user));
		Person person = new Person();
		person.setUuid("uuid");
		person.setContactInfoUuid("contactInfoUuid");
		when(personDAO.getByUuid(anyString())).thenReturn(Optional.of(person));
		when(contactInfoDAO.getByUuid(eq(person.getContactInfoUuid()))).thenReturn(Optional.of(new ContactInfo()));

		personService.updateAll(personWS, accessToken);
		verify(userDAO, times(1)).getByAccessToken(eq(accessToken));
		verify(contactInfoDAO, times(1)).update(any(ContactInfo.class));
		verify(personDAO, times(1)).updateAll(any(Person.class));
		verify(personDAO, times(1)).getByUuid(anyString());
		verify(contactInfoDAO, times(1)).getByUuid(eq(person.getContactInfoUuid()));
	}

	@Test
	public void testUpdate() {
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

		Person person = new Person();
		person.setUuid("uuid");
		person.setContactInfoUuid("contactInfoUuid");
		when(personDAO.getByUuid(anyString())).thenReturn(Optional.of(person));
		when(contactInfoDAO.getByUuid(eq(person.getContactInfoUuid()))).thenReturn(Optional.of(new ContactInfo()));

		personService.update(personWS);
		verify(personDAO, times(1)).update(any(Person.class));
		verify(personDAO, times(1)).getByUuid(anyString());
	}

	@Test
	public void testDelete() {
		String uuid = "uuid";
		Person person = new Person();
		person.setUuid(uuid);
		person.setContactInfoUuid("contactInfoUuid");
		when(personDAO.getByUuid(eq(uuid))).thenReturn(Optional.of(person));

		personService.delete(uuid);
		verify(achievementDAO, times(1)).deleteAllByPerson(eq(uuid));
		verify(educationDAO, times(1)).deleteAllByPerson(eq(uuid));
		verify(experienceDAO, times(1)).deleteAllByPerson(eq(uuid));
		verify(skillDAO, times(1)).deleteAllByPerson(eq(uuid));
		verify(personDAO, times(1)).delete(eq(uuid));
		verify(contactInfoDAO, times(1)).delete(eq(person.getContactInfoUuid()));
	}

	private void validate(PersonWS personWS, Person person) {
		assertThat(personWS.getUuid()).isEqualTo(person.getUuid());
		assertThat(personWS.getBirthDate()).isEqualTo(person.getBirthDate());
		assertThat(personWS.getFirstName()).isEqualTo(person.getFirstName());
		assertThat(personWS.getLastName()).isEqualTo(person.getLastName());
		assertThat(personWS.getOverview()).isEqualTo(person.getOverview());
		assertThat(personWS.getLinkedInUrl()).isEqualTo(person.getLinkedInUrl());
		assertThat(personWS.getGithubUrl()).isEqualTo(person.getGithubUrl());
		assertThat(personWS.getFacebookUrl()).isEqualTo(person.getFacebookUrl());
		assertThat(personWS.getTwitterUrl()).isEqualTo(person.getTwitterUrl());
	}
}
