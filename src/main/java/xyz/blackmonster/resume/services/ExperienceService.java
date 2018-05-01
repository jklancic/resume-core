package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.ExperienceWS;

/**
 * Experience service interface
 */
public interface ExperienceService {

	/**
	 * Returns all experience entries for specific person.
	 * @param personUuid
	 * @return
	 */
	List<ExperienceWS> getAllByPerson(String personUuid);

	/**
	 * Returns experience entry for specific education uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	ExperienceWS getByUuid(String uuid, String personUuid);
}
