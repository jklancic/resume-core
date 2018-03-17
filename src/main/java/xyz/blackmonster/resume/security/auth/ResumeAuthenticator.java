package xyz.blackmonster.resume.security.auth;

import java.util.Optional;

import javax.inject.Inject;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import xyz.blackmonster.resume.security.model.User;
import xyz.blackmonster.resume.security.repository.UserDAO;
import xyz.blackmonster.resume.security.util.PasswordUtil;

/**
 * Authenticator to verify user credentials.
 */
public class ResumeAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private UserDAO userDAO;
	
	@Inject
	public ResumeAuthenticator(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		try {
			Optional<User> optionalUser = retrieveUser(credentials.getUsername());
			if(optionalUser.isPresent() &&
				PasswordUtil.verifyUserPassword(optionalUser.get().getPassword(), credentials.getPassword())) {
				return optionalUser;
			}
			return Optional.empty();
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
	}

	/**
	 * Searching for username in the DB.
	 * 
	 * @param username
	 * @return
	 */
	private Optional<User> retrieveUser(String username) throws Exception {
		return Optional.ofNullable(userDAO.getByUsername(username));
	}
}
