package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;
import xyz.blackmonster.resume.repositories.dao.UserDAO;
import xyz.blackmonster.resume.security.util.PasswordUtil;
import xyz.blackmonster.resume.ws.mapper.UserWSMapper;
import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * User service
 */
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Inject
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<UserWS> getAll() {
		return userDAO.getAll().stream().map(UserWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	public UserWS getByUuid(String uuid) {
		return getUser(
			userDAO.getByUuid(uuid),
			String.format("Could not find any user with uuid=%s", uuid));
	}

	@Override
	public UserWS getByUsername(String username) {
		return getUser(
			userDAO.getByUsername(username),
			String.format("Could not find any user with username=%s", username));
	}

	private UserWS getUser(Optional<User> optionalUser, String exceptionMessage) {
		if(!optionalUser.isPresent()) {
			throw new NotFoundException(exceptionMessage);
		}
		return UserWSMapper.toWS(optionalUser.get());
	}

	@Override
	public UserWS createUser(UserWS userWS) {
		String uuid = UUID.randomUUID().toString();
		userDAO.create(uuid, userWS.getUsername(), userWS.getPassword(), Role.valueOf(userWS.getRole()).getRoleIndex());
		return getByUuid(uuid);
	}

	@Override
	public UserWS updateUser(String uuid, UserWS userWS) {
		userDAO.update(
			userWS.getUsername(),
			PasswordUtil.generateSecurePassword(userWS.getPassword()),
			Role.valueOf(userWS.getRole()).getRoleIndex(),
			uuid);
		return getByUuid(userWS.getUuid());
	}

	@Override
	public void deleteUser(String uuid) {
		userDAO.delete(uuid);
	}
}
