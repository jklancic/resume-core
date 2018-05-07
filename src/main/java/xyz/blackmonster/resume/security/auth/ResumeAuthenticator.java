package xyz.blackmonster.resume.security.auth;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.repositories.dao.UserDAO;

public class ResumeAuthenticator implements Authenticator<CustomCredentials, ResumeAuthUser> {

	private UserDAO userDAO;

	private PersonDAO personDAO;

	@Inject
	public ResumeAuthenticator(UserDAO userDAO, PersonDAO personDAO) {
		this.userDAO = userDAO;
		this.personDAO = personDAO;
	}

	@Override
	public Optional<ResumeAuthUser> authenticate(CustomCredentials credentials) throws AuthenticationException {
		if(credentials.getAccessToken() == null || credentials.getAccessToken().isEmpty()) {
			throw new AuthenticationException("Access token is not valid.");
		}
		// TODO: Validate token first and throw AuthenticationException if expired, and then search for user.
		Optional<User> optionalUser = userDAO.getByAccessToken(credentials.getAccessToken());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(user.getRole().equals(Role.USER)) {
				List<String> uuidList = personDAO.getAllPersonUuidByOwnerUuid(user.getUuid());
				return Optional.of(
					new ResumeAuthUser(
						user.getUuid(),
						user.getUsername(),
						user.getPassword(),
						user.getRole(),
						uuidList));
			}
			return Optional.of(
				new ResumeAuthUser(
					user.getUuid(),
					user.getUsername(),
					user.getPassword(),
					user.getRole(),
					Collections.EMPTY_LIST));
		}
		return Optional.empty();
	}
}
