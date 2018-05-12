package xyz.blackmonster.resume.model.builder;

import xyz.blackmonster.resume.model.Skill;

/**
 * Skill builder
 */
public class SkillBuilder implements Builder<Skill> {

	private Skill skill;

	public SkillBuilder() {
		skill = new Skill();
	}

	public SkillBuilder uuid(String uuid) {
		skill.setUuid(uuid);
		return this;
	}

	public SkillBuilder mastery(String mastery) {
		skill.setMastery(mastery);
		return this;
	}

	public SkillBuilder level(int level) {
		skill.setLevel(level);
		return this;
	}

	public SkillBuilder personUuid(String personUuid) {
		skill.setPersonUuid(personUuid);
		return this;
	}

	@Override
	public Skill build() {
		return skill;
	}
}
