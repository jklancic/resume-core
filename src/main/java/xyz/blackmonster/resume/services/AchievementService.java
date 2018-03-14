package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.models.Achievement;

public interface AchievementService {

	/**
	 * Returns all achievements for specific user.
	 * @param userUuid
	 * @return
	 */
	List<Achievement> getAllByUser(String userUuid);

	/**
	 * Returns achievement for specific uuid.
	 * @param uuid
	 * @return
	 */
	Achievement getByUuid(String uuid, String userUuid);
}
