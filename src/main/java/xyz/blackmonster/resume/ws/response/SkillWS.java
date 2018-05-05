package xyz.blackmonster.resume.ws.response;

/**
 * Web Service response object for Skill
 */
public class SkillWS {

	private String uuid;
	private String mastery;
	private int level;
	
	public SkillWS() {
		
	}

	public SkillWS(String uuid, String mastery, int level) {
		this.uuid = uuid;
		this.mastery = mastery;
		this.level = level;
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
