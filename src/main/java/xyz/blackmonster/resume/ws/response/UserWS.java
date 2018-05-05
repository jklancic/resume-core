package xyz.blackmonster.resume.ws.response;

public class UserWS {

	private String uuid;
	private String username;
	private String password;
	private String role;

	public UserWS() {

	}

	public UserWS(String uuid, String username, String password, String role) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
