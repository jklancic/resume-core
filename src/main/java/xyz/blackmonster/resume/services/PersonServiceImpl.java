package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.repositories.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.ws.mapper.PersonWSMapper;
import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service
 */
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	private ContactInfoDAO contactInfoDAO;

	@Inject
	public PersonServiceImpl(PersonDAO personDAO, ContactInfoDAO contactInfoDAO) {
		this.personDAO = personDAO;
		this.contactInfoDAO = contactInfoDAO;
	}

	@Override
	public List<PersonWS> getAll() {
		return null;
	}

	@Override
	public PersonWS getByUuid(String uuid) {
		Optional<Person> optionalExperience = personDAO.getByUuid(uuid);
		if(!optionalExperience.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any person entry with uuid=%s", uuid));
		}
		Optional<ContactInfo> optionalContactInfo = contactInfoDAO.getByUuid(optionalExperience.get().getContactInfoUuid());
		if(!optionalContactInfo.isPresent()) {
			throw new IllegalStateException(
				String.format("Could not find contact information with uuid=%s and person uuid=%s",
					optionalExperience.get().getContactInfoUuid(), uuid));
		}
		return PersonWSMapper.toWS(optionalExperience.get(), optionalContactInfo.get());
	}
}
