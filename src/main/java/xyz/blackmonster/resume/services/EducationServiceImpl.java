package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

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
	public List<EducationWS> getAllByPerson(String personUuid) {
		return educationDAO.getAllByPerson(personUuid).stream().map(EducationWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	public EducationWS getByUuid(String uuid, String personUuid) {
		Optional<Education> optionalEducation = educationDAO.getByUuid(uuid, personUuid);
		if(!optionalEducation.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any education entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		return EducationWSMapper.toWS(optionalEducation.get());
	}
}
