package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * User service interface
 */
public interface UserService {

	List<UserWS> getAll();

	UserWS getByUuid(String uuid);

	UserWS getByUsername(String username);

	UserWS createUser(UserWS userWS);

	UserWS updateUser(String uuuid, UserWS userWS);

	void deleteUser(String uuid);
}
