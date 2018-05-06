package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.repositories.dao.SkillDAO;
import xyz.blackmonster.resume.ws.mapper.SkillWSMapper;
import xyz.blackmonster.resume.ws.response.SkillWS;

/**
 * Skill service
 */
public class SkillServiceImpl implements SkillService {

	private SkillDAO skillDAO;

	@Inject
	public SkillServiceImpl(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	@Override
	@Transaction
	public List<SkillWS> getAllByPerson(String personUuid) {
		return skillDAO.getAllByPerson(personUuid).stream()
			.map(skill -> SkillWSMapper.toWS(skill)).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public SkillWS getByUuid(String uuid, String personUuid) {
		Optional<Skill> optionalSkill = skillDAO.getByUuid(uuid, personUuid);
		if(!optionalSkill.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any skill entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}

		return SkillWSMapper.toWS(optionalSkill.get());
	}

	@Override
	@Transaction
	public SkillWS create(SkillWS skillWS, String personUuid) {
		skillWS.setUuid(UUID.randomUUID().toString());
		skillDAO.create(SkillWSMapper.toModel(skillWS, personUuid));
		return getByUuid(skillWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public SkillWS update(SkillWS skillWS, String personUuid) {
		skillDAO.update(SkillWSMapper.toModel(skillWS, personUuid));
		return getByUuid(skillWS.getUuid(), personUuid);
	}

	@Override
	@Transaction
	public void delete(String uuid) {
		skillDAO.delete(uuid);
	}
}
