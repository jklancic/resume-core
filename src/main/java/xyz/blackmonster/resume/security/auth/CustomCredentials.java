package xyz.blackmonster.resume.security.auth;

public class CustomCredentials {

	private String accessToken;

	public CustomCredentials() {
	}

	public CustomCredentials(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
