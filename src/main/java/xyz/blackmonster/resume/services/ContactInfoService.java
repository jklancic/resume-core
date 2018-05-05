package xyz.blackmonster.resume.services;

import xyz.blackmonster.resume.ws.response.ContactInfoWS;

/**
 * Contact infor service interface
 */
public interface ContactInfoService {
	
	ContactInfoWS getByUuid(String uuid);

	ContactInfoWS getByPersonUuid(String userUuid);
}
