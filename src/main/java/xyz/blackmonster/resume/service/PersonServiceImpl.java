package xyz.blackmonster.resume.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.Person;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.repository.dao.AchievementDAO;
import xyz.blackmonster.resume.repository.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repository.dao.EducationDAO;
import xyz.blackmonster.resume.repository.dao.ExperienceDAO;
import xyz.blackmonster.resume.repository.dao.PersonDAO;
import xyz.blackmonster.resume.repository.dao.SkillDAO;
import xyz.blackmonster.resume.repository.dao.UserDAO;
import xyz.blackmonster.resume.ws.mapper.ContactInfoWSMapper;
import xyz.blackmonster.resume.ws.mapper.PersonWSMapper;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;
import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service
 */
public class PersonServiceImpl implements PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	private AchievementDAO achievementDAO;

	private ContactInfoDAO contactInfoDAO;

	private EducationDAO educationDAO;

	private ExperienceDAO experienceDAO;

	private PersonDAO personDAO;

	private SkillDAO skillDAO;

	private UserDAO userDAO;

	@Inject
	public PersonServiceImpl(
		AchievementDAO achievementDAO, ContactInfoDAO contactInfoDAO,
		EducationDAO educationDAO, ExperienceDAO experienceDAO,
		PersonDAO personDAO, SkillDAO skillDAO, UserDAO userDAO) {
		this.achievementDAO = achievementDAO;
		this.contactInfoDAO = contactInfoDAO;
		this.educationDAO = educationDAO;
		this.experienceDAO = experienceDAO;
		this.personDAO = personDAO;
		this.skillDAO = skillDAO;
		this.userDAO = userDAO;
	}

	@Override
	@Transaction
	public List<PersonWS> getAll() {
		List<Person> personList = personDAO.getAll();
		return personList.stream().map(person -> PersonWSMapper.toWS(person, null)).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public PersonWS getByUuid(String uuid) {
		Person person = getPerson(uuid);
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByUuid(person.getContactInfoUuid());
		if(!optionalContactInfo.isPresent()) {
			throw new IllegalStateException(
				String.format("Could not find contact information with uuid=%s and person uuid=%s",
					person.getContactInfoUuid(), uuid));
		}
		return PersonWSMapper.toWS(person, optionalContactInfo.get());
	}

	@Override
	public String getUuid(String url) {
		String baseUrl = getBaseURL(url);
		Optional<String> optionalString = personDAO.getUuidByBaseUrl(baseUrl);
		if (!optionalString.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any person for URL=%s", url));
		}
		return optionalString.get();
	}

	private String getBaseURL(String urlAsString) throws IllegalArgumentException {
		try {
			URL url = new URL(urlAsString);
			return new URL(url.getProtocol(), url.getHost(), url.getPort(), null).toString();
		} catch (MalformedURLException e) {
			LOGGER.error("Given URI is not valid.");
			throw new IllegalArgumentException("Given URI is not valid.", e);
		}
	}

	@Override
	@Transaction
	public PersonWS create(PersonWS personWS, String accessToken) {
		String userUuid = getUserUuid(accessToken);
		ContactInfoWS contactInfoWS = personWS.getContactInfo();
		contactInfoWS.setUuid(UUID.randomUUID().toString());
		contactInfoDAO.create(ContactInfoWSMapper.toModel(contactInfoWS));
		personWS.setUuid(UUID.randomUUID().toString());
		personDAO.create(PersonWSMapper.toModel(personWS, userUuid));
		return getByUuid(personWS.getUuid());
	}

	@Override
	@Transaction
	public PersonWS updateAll(PersonWS personWS, String accessToken) {
		String userUuid = getUserUuid(accessToken);
		ContactInfoWS contactInfoWS = personWS.getContactInfo();
		if(contactInfoWS != null) {
			contactInfoDAO.update(ContactInfoWSMapper.toModel(contactInfoWS));
		}
		personDAO.updateAll(PersonWSMapper.toModel(personWS, userUuid));
		return getByUuid(personWS.getUuid());
	}

	@Override
	@Transaction
	public PersonWS update(PersonWS personWS) {
		personDAO.update(PersonWSMapper.toModel(personWS, null));
		return getByUuid(personWS.getUuid());
	}

	@Override
	@Transaction
	public void delete(String uuid) {
		Person person = getPerson(uuid);
		String contactInfoUuid = person.getContactInfoUuid();
		achievementDAO.deleteAllByPerson(uuid);
		educationDAO.deleteAllByPerson(uuid);
		experienceDAO.deleteAllByPerson(uuid);
		skillDAO.deleteAllByPerson(uuid);
		personDAO.delete(uuid);
		contactInfoDAO.delete(contactInfoUuid);
	}

	private Person getPerson(String uuid) {
		Optional<Person> optionalPerson = personDAO.getByUuid(uuid);
		if (!optionalPerson.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any person entry with uuid=%s", uuid));
		}
		return optionalPerson.get();
	}

	private String getUserUuid(String accessToken) {
		Optional<User> optionalUser = userDAO.getByAccessToken(accessToken);
		if(!optionalUser.isPresent()) {
			// TODO: remove access token from DB if exists
			throw new IllegalStateException("Access token does not belong to any user.");
		}
		return optionalUser.get().getUuid();
	}
}
