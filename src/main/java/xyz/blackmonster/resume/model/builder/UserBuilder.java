package xyz.blackmonster.resume.model.builder;

import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;

/**
 * User builder
 */
public class UserBuilder implements Builder<User> {

	private User user;

	public UserBuilder() {
		user = new User();
	}

	public UserBuilder uuid(String uuid) {
		user.setUuid(uuid);
		return this;
	}

	public UserBuilder username(String username) {
		user.setUsername(username);
		return this;
	}

	public UserBuilder password(String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder role(Role role) {
		user.setRole(role);
		return this;
	}

	@Override
	public User build() {
		return user;
	}
}
