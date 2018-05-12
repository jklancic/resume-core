package xyz.blackmonster.resume.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.model.Experience;
import xyz.blackmonster.resume.repository.dao.ExperienceDAO;
import xyz.blackmonster.resume.ws.mapper.ExperienceWSMapper;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

/**
 * Experience service
 */
public class ExperienceServiceImpl implements ExperienceService {

	private ExperienceDAO experienceDAO;

	@Inject
	public ExperienceServiceImpl(ExperienceDAO experienceDAO) {
		this.experienceDAO = experienceDAO;
	}

	@Override
	@Transaction
	public List<ExperienceWS> getAllByPerson(String personUuid) {
		return experienceDAO.getAllByPerson(personUuid).stream().map(ExperienceWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public ExperienceWS getByUuid(String uuid, String personUuid) {
		Optional<Experience> optionalExperience = experienceDAO.getByUuid(uuid, personUuid);
		if(!optionalExperience.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any experience entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return ExperienceWSMapper.toWS(optionalExperience.get());
	}

	@Override
	@Transaction
	public ExperienceWS create(ExperienceWS experienceWS, String personUuid) {
		experienceWS.setUuid(UUID.randomUUID().toString());
		experienceDAO.create(ExperienceWSMapper.toModel(experienceWS, personUuid));
		return getByUuid(experienceWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public ExperienceWS update(ExperienceWS experienceWS, String personUuid) {
		experienceDAO.update(ExperienceWSMapper.toModel(experienceWS, personUuid));
		return getByUuid(experienceWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public void delete(String uuid) {
		experienceDAO.delete(uuid);
	}
}
