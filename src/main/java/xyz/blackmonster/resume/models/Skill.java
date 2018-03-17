package xyz.blackmonster.resume.models;

/**
 * Skill
 */
public class Skill {
	
	private String mastery;
	private int level;
	private String categoryUuid;
	private String personUuid;
	
	public Skill() {
		
	}

	public Skill(String mastery, int level, String categoryUuid, String personUuid) {
		this.mastery = mastery;
		this.level = level;
		this.categoryUuid = categoryUuid;
		this.personUuid = personUuid;
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

	public String getCategoryUuid() {
		return categoryUuid;
	}

	public void setCategoryUuid(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}

	public String getPersonUuid() {
		return personUuid;
	}

	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}
}
