package xyz.blackmonster.resume.security.auth;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.repository.dao.PersonDAO;
import xyz.blackmonster.resume.repository.dao.UserDAO;
import xyz.blackmonster.resume.service.JWTService;

public class ResumeAuthenticator implements Authenticator<CookieToken, ResumeAuthUser> {

	private UserDAO userDAO;

	private PersonDAO personDAO;

	private JWTService jwtService;

	@Inject
	public ResumeAuthenticator(UserDAO userDAO, PersonDAO personDAO, JWTService jwtService) {
		this.userDAO = userDAO;
		this.personDAO = personDAO;
		this.jwtService = jwtService;
	}

	@Override
	public Optional<ResumeAuthUser> authenticate(CookieToken credentials) throws AuthenticationException {
		if(credentials.getAccessToken() == null || credentials.getAccessToken().isEmpty()) {
			throw new AuthenticationException("Access token is not valid.");
		}

		String userUuid = retrieveUserUuid(credentials.getAccessToken());
		Optional<User> optionalUser = userDAO.getByAccessToken(credentials.getAccessToken());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			return getResumeAuthUser(userUuid, user);
		}
		userDAO.updateAccessToken(userUuid, credentials.getAccessToken());
		optionalUser = userDAO.getByUuid(userUuid);
		if(!optionalUser.isPresent()) {
			throw new AuthenticationException("Access token is not valid.");
		}
		return getResumeAuthUser(userUuid, optionalUser.get());
	}

	private Optional<ResumeAuthUser> getResumeAuthUser(String userUuid, User user) throws AuthenticationException {
		if(user.getUuid().equals(userUuid)) {
			throw new AuthenticationException("Access token is not valid.");
		}
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

	private String retrieveUserUuid(String accessToken) {
		// TODO: Retrieve the user uuid from the payload
		String userUuid = jwtService.retrievePayloadFromToken(accessToken);
		return userUuid;
	}
}
