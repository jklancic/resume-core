package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.Category;
import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.repositories.dao.CategoryDAO;
import xyz.blackmonster.resume.repositories.dao.SkillDAO;
import xyz.blackmonster.resume.ws.mapper.SkillWSMapper;
import xyz.blackmonster.resume.ws.response.SkillWS;

/**
 * Skill service
 */
public class SkillServiceImpl implements SkillService {

	private SkillDAO skillDAO;

	private CategoryDAO categoryDAO;

	@Inject
	public SkillServiceImpl(SkillDAO skillDAO, CategoryDAO categoryDAO) {
		this.skillDAO = skillDAO;
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<SkillWS> getAllByPerson(String personUuid) {
		return skillDAO.getAllByPerson(personUuid).stream()
			.map(skill -> SkillWSMapper.toWS(skill, getCategoryByUuid(skill.getCategoryUuid()))).collect(Collectors.toList());
	}

	@Override
	public SkillWS getByUuid(String uuid, String personUuid) {
		Optional<Skill> optionalSkill = skillDAO.getByUuid(uuid, personUuid);
		if(!optionalSkill.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any skill entry with uuid=%s and person uuid=%s", uuid, personUuid));
		}
		Category category = getCategoryByUuid(optionalSkill.get().getCategoryUuid());

		return SkillWSMapper.toWS(optionalSkill.get(), category);
	}

	private Category getCategoryByUuid(String categoryUuid) {
		Optional<Category> optionalCategory = categoryDAO.getByUuid(categoryUuid);
		if(!optionalCategory.isPresent()) {
			throw new IllegalStateException(
				String.format("Could not find category with uuid=%s", categoryUuid));
		}

		return optionalCategory.get();
	}
}
