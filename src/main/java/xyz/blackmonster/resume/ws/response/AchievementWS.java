package xyz.blackmonster.resume.ws.response;

import java.sql.Date;

import xyz.blackmonster.resume.model.Achievement;

/**
 * Web Service response object for Achievement
 */
public class AchievementWS {

	private String uuid;
	private Date date;
	private String description;
	
	public AchievementWS() {
		
	}
	
	public AchievementWS(Achievement achievement) {
		this.uuid = achievement.getUuid();
		this.date = achievement.getDate();
		this.description = achievement.getDescription();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
