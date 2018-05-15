package xyz.blackmonster.resume.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.NotFoundException;

import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.auth0.jwt.exceptions.JWTCreationException;
import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.repository.dao.UserDAO;
import xyz.blackmonster.resume.security.util.PasswordUtil;
import xyz.blackmonster.resume.ws.mapper.UserWSMapper;
import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * User service
 */
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	private JWTService jwtService;

	@Inject
	public UserServiceImpl(UserDAO userDAO, JWTService jwtService) {
		this.userDAO = userDAO;
		this.jwtService = jwtService;
	}

	@Override
	@Transaction
	public List<UserWS> getAll() {
		return userDAO.getAll().stream().map(UserWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	@Transaction
	public UserWS getByUuid(String uuid) {
		return getUser(
			userDAO.getByUuid(uuid),
			String.format("Could not find any user with uuid=%s", uuid));
	}

	@Override
	@Transaction
	public UserWS getByUsername(String username) {
		return getUser(
			userDAO.getByUsername(username),
			String.format("Could not find any user with username=%s", username));
	}

	@Override
	@Transaction
	public String authenticateUser(String username, String password) throws AuthenticationException, JWTCreationException {
		User user = userDAO.getByUsername(username).orElseThrow(() -> new AuthenticationException("Credentials are not valid."));
		if(!PasswordUtil.verifyUserPassword(password, user.getPassword())) {
			throw new AuthenticationException("Credentials are not valid.");
		}
		return jwtService.createToken(user.getUuid(), user.getUsername(), user.getRole().name());
	}

	private UserWS getUser(Optional<User> optionalUser, String exceptionMessage) {
		if(!optionalUser.isPresent()) {
			throw new NotFoundException(exceptionMessage);
		}
		return UserWSMapper.toWS(optionalUser.get());
	}

	@Override
	@Transaction
	public UserWS createUser(UserWS userWS) {
		String uuid = UUID.randomUUID().toString();
		userDAO.create(
			uuid,
			userWS.getUsername(),
			PasswordUtil.generateSecurePassword(userWS.getPassword()),
			Role.valueOf(userWS.getRole()).getRoleIndex());
		return getByUuid(uuid);
	}

	@Override
	@Transaction
	public UserWS updateUser(UserWS userWS) {
		userDAO.update(
			userWS.getUsername(),
			PasswordUtil.generateSecurePassword(userWS.getPassword()),
			Role.valueOf(userWS.getRole()).getRoleIndex(),
			userWS.getUuid());
		return getByUuid(userWS.getUuid());
	}

	@Override
	@Transaction
	public void deleteUser(String uuid) {
		userDAO.delete(uuid);
	}

	@Override
	public void deleteAccessToken(String accessToken) {

	}


}
