package xyz.blackmonster.resume.services;

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
	 * @param userUuid
	 * @return
	 */
	ContactInfoWS getByPersonUuid(String userUuid);

	/**
	 * Update and return contact info.
	 * @param contactInfoWS
	 * @return
	 */
	ContactInfoWS update(ContactInfoWS contactInfoWS);
}
