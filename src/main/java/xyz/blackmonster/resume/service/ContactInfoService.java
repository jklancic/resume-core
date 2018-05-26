package xyz.blackmonster.resume.service;

import xyz.blackmonster.resume.ws.response.ContactInfoWS;

/**
 * Contact infor service interface
 */
public interface ContactInfoService {

	/**
	 * Return contact info by uuid.
	 * @param uuid
	 * @return
	 */
	ContactInfoWS getByUuid(String uuid);

	/**
	 * Return contact info by person uuid.
	 * @param personUuid
	 * @return
	 */
	ContactInfoWS getByPersonUuid(String personUuid);

	/**
	 * Update and return contact info.
	 * @param contactInfoWS
	 * @return
	 */
	ContactInfoWS update(ContactInfoWS contactInfoWS);
}
