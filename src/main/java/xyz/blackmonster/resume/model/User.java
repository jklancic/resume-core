package xyz.blackmonster.resume.model;

import java.security.Principal;

/**
 * Authenticated user.
 */
public class User implements Principal {
	
	private String uuid;
	private String username;
	private String password;
	private Role role;

	public User() {

	}

	public User(String uuid, String username, String password, Role role) {
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String getName() {
		return getRole().name();
	}
}
