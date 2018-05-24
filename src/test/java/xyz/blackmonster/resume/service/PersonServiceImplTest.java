package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.Person;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;
import xyz.blackmonster.resume.model.builder.PersonBuilder;
import xyz.blackmonster.resume.repository.dao.AchievementDAO;
import xyz.blackmonster.resume.repository.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repository.dao.EducationDAO;
import xyz.blackmonster.resume.repository.dao.ExperienceDAO;
import xyz.blackmonster.resume.repository.dao.PersonDAO;
import xyz.blackmonster.resume.repository.dao.SkillDAO;
import xyz.blackmonster.resume.repository.dao.UserDAO;
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
