package xyz.blackmonster.resume.ws.response;

import xyz.blackmonster.resume.models.User;

public class UserWS {

	private String uuid;
	private String username;
	private String password;
	private String role;

	public UserWS() {

	}

	public UserWS(User user) {
		this.uuid = user.getUuid();
		this.username = user.getUsername();
		this.role = user.getRole().name();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
