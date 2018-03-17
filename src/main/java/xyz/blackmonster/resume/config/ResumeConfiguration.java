package xyz.blackmonster.resume.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilderSpec;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ResumeConfiguration extends Configuration {
	
	private static final String DATABASE = "database";
	private static final String AUTH_CACHE_POLICY = "authenticationCachePolicy";
	
	@Valid
	@NotNull
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	@NotEmpty
	private String authenticationCachePolicy;

	@JsonProperty(DATABASE)
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	@JsonProperty(AUTH_CACHE_POLICY)
	public String getAuthenticationCachePolicy() {
		return authenticationCachePolicy;
	}
}
