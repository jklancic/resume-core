package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.ws.AchievementWS;

/**
 * Mapper to convert from and to AchievementWS
 */
public class AchievementWSMapper {
	
	public static AchievementWS convert(Achievement achievement) {
		return new AchievementWS(
			achievement.getUuid(), achievement.getDate(), achievement.getDescription(), achievement.getUserUuid());
	}
	
	public static Achievement convert(AchievementWS achievementWS) {
		return new Achievement(
			achievementWS.getUuid(), achievementWS.getDate(), achievementWS.getDescription(), achievementWS.getUserUuid());
	}
}
