package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.ws.response.SkillWS;

/**
 * Mapper for transforming to and from SkillWS
 */
public class SkillWSMapper {

	public static SkillWS toWS(Skill skill) {
		return new SkillWS(
			skill.getUuid(),
			skill.getMastery(),
			skill.getLevel());
	}

	public static Skill toModel(SkillWS skillWS, String personUuid) {
		return new Skill(
			skillWS.getUuid(),
			skillWS.getMastery(),
			skillWS.getLevel(),
			personUuid);
	}
}
