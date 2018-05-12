package xyz.blackmonster.resume.services;

import java.util.List;

import javax.naming.AuthenticationException;

import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * User service interface
 */
public interface UserService {

	/**
	 * Returns all users.
	 * @return
	 */
	List<UserWS> getAll();

	/**
	 * Return user by user uuid.
	 * @param uuid
	 * @return
	 */
	UserWS getByUuid(String uuid);

	/**
	 * Return user by username.
	 * @param username
	 * @return
	 */
	UserWS getByUsername(String username);

	/**
	 * Validates credentials and returns access token.
	 * @param username
	 * @param password
	 * @return
	 */
	String authenticateUser(String username, String password) throws AuthenticationException;

	/**
	 * Create and return new user.
	 * @param userWS
	 * @return
	 */
	UserWS createUser(UserWS userWS);

	/**
	 * Update and return updated user.
	 * @param userWS
	 * @return
	 */
	UserWS updateUser(UserWS userWS);

	/**
	 * Delete user.
	 * @param uuid
	 */
	void deleteUser(String uuid);
}
