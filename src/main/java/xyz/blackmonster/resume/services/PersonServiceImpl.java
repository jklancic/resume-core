package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.repositories.dao.AchievementDAO;
import xyz.blackmonster.resume.repositories.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repositories.dao.EducationDAO;
import xyz.blackmonster.resume.repositories.dao.ExperienceDAO;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.repositories.dao.SkillDAO;
import xyz.blackmonster.resume.ws.mapper.ContactInfoWSMapper;
import xyz.blackmonster.resume.ws.mapper.PersonWSMapper;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;
import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service
 */
public class PersonServiceImpl implements PersonService {

	private AchievementDAO achievementDAO;

	private ContactInfoDAO contactInfoDAO;

	private EducationDAO educationDAO;

	private ExperienceDAO experienceDAO;

	private PersonDAO personDAO;

	private SkillDAO skillDAO;

	@Inject
	public PersonServiceImpl(
		AchievementDAO achievementDAO, ContactInfoDAO contactInfoDAO,
		EducationDAO educationDAO, ExperienceDAO experienceDAO,
		PersonDAO personDAO, SkillDAO skillDAO) {
		this.achievementDAO = achievementDAO;
		this.contactInfoDAO = contactInfoDAO;
		this.educationDAO = educationDAO;
		this.experienceDAO = experienceDAO;
		this.personDAO = personDAO;
		this.skillDAO = skillDAO;
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
	@Transaction
	public PersonWS create(PersonWS personWS, String userUuid) {
		ContactInfoWS contactInfoWS = personWS.getContactInfo();
		contactInfoWS.setUuid(UUID.randomUUID().toString());
		contactInfoDAO.create(ContactInfoWSMapper.toModel(contactInfoWS));
		personWS.setUuid(UUID.randomUUID().toString());
		personDAO.create(PersonWSMapper.toModel(personWS, userUuid));
		return getByUuid(personWS.getUuid());
	}

	@Override
	@Transaction
	public PersonWS update(PersonWS personWS, String userUuid) {
		ContactInfoWS contactInfoWS = personWS.getContactInfo();
		if(contactInfoWS != null) {
			contactInfoDAO.update(ContactInfoWSMapper.toModel(contactInfoWS));
		}
		personDAO.update(PersonWSMapper.toModel(personWS, userUuid));
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
}
