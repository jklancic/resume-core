package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service interface
 */
public interface PersonService {

	/**
	 * Return all persons.
	 * @return
	 */
	List<PersonWS> getAll();

	/**
	 * Return person by uuid.
	 * @param uuid
	 * @return
	 */
	PersonWS getByUuid(String uuid);

	/**
	 * Create and return person.
	 * @param personWS
	 * @return
	 */
	PersonWS create(PersonWS personWS, String userUuid);

	/**
	 * Update and return person.
	 * @param personWS
	 * @return
	 */
	PersonWS update(PersonWS personWS, String userUuid);

	/**
	 * Delete person.
	 * @param uuid
	 */
	void delete(String uuid);
}
