package xyz.blackmonster.resume.service;

import java.util.List;

import xyz.blackmonster.resume.ws.response.EducationWS;

/**
 * Education service interface
 */
public interface EducationService {

	/**
	 * Returns all education entries for specific person.
	 * @param personUuid
	 * @return
	 */
	List<EducationWS> getAllByPerson(String personUuid);

	/**
	 * Returns education entry for specific education uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	EducationWS getByUuid(String uuid, String personUuid);

	/**
	 * Create and return new education.
	 * @param experienceWS
	 * @param personUuid
	 * @return
	 */
	EducationWS create(EducationWS experienceWS, String personUuid);

	/**
	 * Update and return updated education.
	 * @param experienceWS
	 * @param personUuid
	 * @return
	 */
	EducationWS update(EducationWS experienceWS, String personUuid);

	/**
	 * Delete education.
	 * @param uuid
	 */
	void delete(String uuid);
}
