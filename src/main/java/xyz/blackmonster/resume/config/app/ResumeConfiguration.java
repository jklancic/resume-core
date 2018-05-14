package xyz.blackmonster.resume.config.app;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ResumeConfiguration extends Configuration {
	
	private static final String DATABASE = "database";
	private static final String AUTH_CACHE_POLICY = "authenticationCachePolicy";
	private static final String JWT_SECRET = "jwtSecret";
	
	@Valid
	@NotNull
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	@NotEmpty
	private String jwtSecret;

	@JsonProperty(DATABASE)
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	@JsonProperty(JWT_SECRET)
	public String getJwtSecret() {
		return jwtSecret;
	}
}
