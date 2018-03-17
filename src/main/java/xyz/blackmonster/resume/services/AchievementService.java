package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.ws.AchievementWS;

public interface AchievementService {

	/**
	 * Returns all achievements for specific person.
	 * @param personUuid
	 * @return
	 */
	List<AchievementWS> getAllByUser(String personUuid);

	/**
	 * Returns achievement for specific achievement uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	AchievementWS getByUuid(String uuid, String personUuid);
}
