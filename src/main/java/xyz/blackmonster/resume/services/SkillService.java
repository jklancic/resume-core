package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.SkillWS;

public interface SkillService {

	/**
	 * Returns all skill entries for specific person.
	 * @param personUuid
	 * @return
	 */
	List<SkillWS> getAllByPerson(String personUuid);

	/**
	 * Returns skill entry for specific skill uuid and person uuid.
	 * @param uuid
	 * @return
	 */
	SkillWS getByUuid(String uuid, String personUuid);
}
