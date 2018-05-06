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

	/**
	 * Create and return new skill.
	 * @param skillWS
	 * @param personUuid
	 * @return
	 */
	SkillWS create(SkillWS skillWS, String personUuid);

	/**
	 * Update and return updated skill.
	 * @param skillWS
	 * @param personUuid
	 * @return
	 */
	SkillWS update(SkillWS skillWS, String personUuid);

	/**
	 * Delete skill.
	 * @param uuid
	 */
	void delete(String uuid);
}
