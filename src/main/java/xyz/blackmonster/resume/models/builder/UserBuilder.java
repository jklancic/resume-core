package xyz.blackmonster.resume.models.builder;

import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;

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
