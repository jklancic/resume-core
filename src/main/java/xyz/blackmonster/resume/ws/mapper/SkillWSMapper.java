package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.models.builder.SkillBuilder;
import xyz.blackmonster.resume.ws.response.SkillWS;

/**
 * Mapper for transforming to and from SkillWS
 */
public class SkillWSMapper {

	public static SkillWS toWS(Skill skill) {
		return new SkillWS(skill);
	}

	public static Skill toModel(SkillWS skillWS, String personUuid) {
		return new SkillBuilder()
			.uuid(skillWS.getUuid())
			.mastery(skillWS.getMastery())
			.level(skillWS.getLevel())
			.personUuid(personUuid)
			.build();
	}
}
