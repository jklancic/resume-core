package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.Experience;
import xyz.blackmonster.resume.repositories.dao.ExperienceDAO;
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
	public List<ExperienceWS> getAllByPerson(String personUuid) {
		return experienceDAO.getAllByPerson(personUuid).stream().map(ExperienceWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	public ExperienceWS getByUuid(String uuid, String personUuid) {
		Optional<Experience> optionalExperience = experienceDAO.getByUuid(uuid, personUuid);
		if(!optionalExperience.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any experience entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return ExperienceWSMapper.toWS(optionalExperience.get());
	}
}
