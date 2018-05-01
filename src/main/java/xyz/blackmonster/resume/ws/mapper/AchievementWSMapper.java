package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Mapper for transforming to and from AchievementWS
 */
public class AchievementWSMapper {
	
	public static AchievementWS toWS(Achievement achievement) {
		return new AchievementWS(
			achievement.getUuid(), achievement.getDate(), achievement.getDescription());
	}
	
	public static Achievement toModel(AchievementWS achievementWS, String personUuid) {
		return new Achievement(
			achievementWS.getUuid(), achievementWS.getDate(), achievementWS.getDescription(), personUuid);
	}
}
