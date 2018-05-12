package xyz.blackmonster.resume.service;

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
	 * Returns experience entry for specific uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	ExperienceWS getByUuid(String uuid, String personUuid);

	/**
	 * Create and return new experience.
	 * @param experienceWS
	 * @param personUuid
	 * @return
	 */
	ExperienceWS create(ExperienceWS experienceWS, String personUuid);

	/**
	 * Update and return updated experience.
	 * @param experienceWS
	 * @param personUuid
	 * @return
	 */
	ExperienceWS update(ExperienceWS experienceWS, String personUuid);

	/**
	 * Delete experience.
	 * @param uuid
	 */
	void delete(String uuid);
}
