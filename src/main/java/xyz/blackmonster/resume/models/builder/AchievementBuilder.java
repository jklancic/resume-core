package xyz.blackmonster.resume.models.builder;

import java.sql.Date;

import xyz.blackmonster.resume.models.Achievement;

/**
 * Achievement builder
 */
public class AchievementBuilder implements Builder<Achievement> {

	private Achievement achievement;

	public AchievementBuilder() {
		achievement = new Achievement();
	}

	public AchievementBuilder uuid(String uuid) {
		achievement.setUuid(uuid);
		return this;
	}

	public AchievementBuilder date(Date date) {
		achievement.setDate(date);
		return this;
	}

	public AchievementBuilder description(String description) {
		achievement.setDescription(description);
		return this;
	}

	public AchievementBuilder personUuid(String personUuid) {
		achievement.setPersonUuid(personUuid);
		return this;
	}

	@Override
	public Achievement build() {
		return achievement;
	}
}
