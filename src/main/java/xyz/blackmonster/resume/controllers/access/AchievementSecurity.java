package xyz.blackmonster.resume.controllers.access;

import xyz.blackmonster.resume.security.models.User;

public interface AchievementSecurity {
	
	boolean canCreateAchievement(User user, String personUuid);

	boolean canUpdateAchievement(User user, String personUuid);

	boolean canDeleteAchievement(User user, String personUuid);
}
