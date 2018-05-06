package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.Education;
import xyz.blackmonster.resume.repositories.dao.EducationDAO;
import xyz.blackmonster.resume.ws.mapper.EducationWSMapper;
import xyz.blackmonster.resume.ws.response.EducationWS;

/**
 * Education service
 */
public class EducationServiceImpl implements EducationService {
	
	private EducationDAO educationDAO;
	
	@Inject
	public EducationServiceImpl(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}
	
	@Override
	@Transaction
	public List<EducationWS> getAllByPerson(String personUuid) {
		return educationDAO.getAllByPerson(personUuid).stream().map(EducationWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public EducationWS getByUuid(String uuid, String personUuid) {
		Optional<Education> optionalEducation = educationDAO.getByUuid(uuid, personUuid);
		if(!optionalEducation.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any education entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return EducationWSMapper.toWS(optionalEducation.get());
	}

	@Override
	@Transaction
	public EducationWS create(EducationWS experienceWS, String personUuid) {
		experienceWS.setUuid(UUID.randomUUID().toString());
		educationDAO.create(EducationWSMapper.toModel(experienceWS, personUuid));
		return getByUuid(experienceWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public EducationWS update(EducationWS experienceWS, String personUuid) {
		educationDAO.update(EducationWSMapper.toModel(experienceWS, personUuid));
		return getByUuid(experienceWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public void delete(String uuid) {
		educationDAO.delete(uuid);
	}
}
