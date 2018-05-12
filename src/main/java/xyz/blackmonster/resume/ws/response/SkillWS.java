package xyz.blackmonster.resume.ws.response;

import xyz.blackmonster.resume.model.Skill;

/**
 * Web Service response object for Skill
 */
public class SkillWS {

	private String uuid;
	private String mastery;
	private int level;
	
	public SkillWS() {
		
	}

	public SkillWS(Skill skill) {
		this.uuid = skill.getUuid();
		this.mastery = skill.getMastery();
		this.level = skill.getLevel();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMastery() {
		return mastery;
	}

	public void setMastery(String mastery) {
		this.mastery = mastery;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
