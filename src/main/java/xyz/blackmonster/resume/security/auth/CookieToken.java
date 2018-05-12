package xyz.blackmonster.resume.security.auth;

public class CookieToken {

	private String accessToken;

	public CookieToken() {
	}

	public CookieToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
