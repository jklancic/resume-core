package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.model.Achievement;
import xyz.blackmonster.resume.model.builder.AchievementBuilder;
import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Mapper for transforming to and from AchievementWS
 */
public class AchievementWSMapper {
	
	public static AchievementWS toWS(Achievement achievement) {
		return new AchievementWS(achievement);
	}
	
	public static Achievement toModel(AchievementWS achievementWS, String personUuid) {
		return new AchievementBuilder()
			.uuid(achievementWS.getUuid())
			.date(achievementWS.getDate())
			.description(achievementWS.getDescription())
			.personUuid(personUuid)
			.build();
	}
}
