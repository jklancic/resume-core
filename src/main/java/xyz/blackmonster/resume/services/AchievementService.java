package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Achievement service interface
 */
public interface AchievementService {

	/**
	 * Returns all achievements for specific person.
	 * @param personUuid
	 * @return
	 */
	List<AchievementWS> getAllByPerson(String personUuid);

	/**
	 * Returns achievement for specific achievement uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	AchievementWS getByUuid(String uuid, String personUuid);

	/**
	 * Create and return new achievement.
	 * @param achievementWS
	 * @param personUuid
	 * @return
	 */
	AchievementWS create(AchievementWS achievementWS, String personUuid);

	/**
	 * Update and return updated achievement.
	 * @param achievementWS
	 * @param personUuid
	 * @return
	 */
	AchievementWS update(AchievementWS achievementWS, String personUuid);

	/**
	 * Delete achievement.
	 * @param uuid
	 */
	void delete(String uuid);
}
