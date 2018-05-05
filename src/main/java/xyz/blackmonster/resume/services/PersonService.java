package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.PersonWS;

/**
 * Person service interface
 */
public interface PersonService {

	List<PersonWS> getAll();

	PersonWS getByUuid(String uuid);
}
