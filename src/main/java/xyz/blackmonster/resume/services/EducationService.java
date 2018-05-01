package xyz.blackmonster.resume.services;

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
}
