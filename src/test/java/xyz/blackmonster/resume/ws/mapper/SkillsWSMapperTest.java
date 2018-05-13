package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import xyz.blackmonster.resume.model.Skill;
import xyz.blackmonster.resume.model.builder.SkillBuilder;
import xyz.blackmonster.resume.ws.response.SkillWS;

public class SkillsWSMapperTest {

	@Test
	public void testToWS() {
		Skill skill = new SkillBuilder()
			.uuid("uuid")
			.mastery("mastery")
			.level(1)
			.build();

		SkillWS skillWS = SkillWSMapper.toWS(skill);
		validate(skill, skillWS);
	}

	@Test
	public void testToModel() {
		SkillWS skillWS = new SkillWS();
		skillWS.setUuid("uuid");
		skillWS.setMastery("mastery");
		skillWS.setLevel(1);

		String personUuid = "personUuid";
		Skill skill = SkillWSMapper.toModel(skillWS, personUuid);
		validate(skill, skillWS);
		assertThat(skill.getPersonUuid()).isEqualTo(personUuid);
	}

	private void validate(Skill skill, SkillWS skillWS) {
		assertThat(skillWS.getUuid()).isEqualTo(skill.getUuid());
		assertThat(skillWS.getMastery()).isEqualTo(skill.getMastery());
		assertThat(skillWS.getLevel()).isEqualTo(skill.getLevel());
	}
}
