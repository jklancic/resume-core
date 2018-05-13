package xyz.blackmonster.resume.config.app;

public class RuntimeConfiguration {

	private static final RuntimeConfiguration configuration = new RuntimeConfiguration();

	private String jwtSecret;

	private RuntimeConfiguration() {

	}

	public static RuntimeConfiguration get() {
		return configuration;
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
}
