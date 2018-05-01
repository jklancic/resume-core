package xyz.blackmonster.resume.services;

import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service interface
 */
public interface PersonService {

	PersonWS getByUuid(String uuid);
}
