package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Category;
import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.ws.response.CategoryWS;
import xyz.blackmonster.resume.ws.response.SkillWS;

/**
 * Mapper for transforming to and from SkillWS
 */
public class SkillWSMapper {

	public static SkillWS toWS(Skill skill, Category category) {
		CategoryWS categoryWS = CategoryWSMapper.toWS(category);
		return new SkillWS(
			skill.getUuid(),
			skill.getMastery(),
			skill.getLevel(),
			categoryWS);
	}

	public static Skill toModel(SkillWS skillWS, String personUuid) {
		return new Skill(
			skillWS.getUuid(),
			skillWS.getMastery(),
			skillWS.getLevel(),
			skillWS.getCategoryWS().getUuid(),
			personUuid);
	}
}
