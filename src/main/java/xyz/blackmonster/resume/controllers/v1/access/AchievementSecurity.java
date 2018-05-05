package xyz.blackmonster.resume.controllers.v1.access;

import xyz.blackmonster.resume.models.User;

public interface AchievementSecurity {
	
	boolean canCreateAchievement(User user, String personUuid);

	boolean canUpdateAchievement(User user, String personUuid);

	boolean canDeleteAchievement(User user, String personUuid);
}
