package xyz.blackmonster.resume.controllers.api.v1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.flywaydb.core.Flyway;
import org.h2.tools.DeleteDbFiles;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import xyz.blackmonster.resume.app.ResumeApplication;
import xyz.blackmonster.resume.config.app.ResumeConfiguration;
import xyz.blackmonster.resume.security.auth.Credentials;
import xyz.blackmonster.resume.security.auth.ResumeAuthFilter;
import xyz.blackmonster.resume.ws.response.UserWS;

/**
 * Integration test for user controller.
 */
public class UserControllerIT {

	private String accessToken;

	@ClassRule
	public static final DropwizardAppRule<ResumeConfiguration> RULE =
		new DropwizardAppRule<ResumeConfiguration>(
			ResumeApplication.class, ResourceHelpers.resourceFilePath("configuration-it.yml"));

	@Before
	public void setUp() {
		Flyway flyway = new Flyway();
		DataSourceFactory dataSourceFactory = RULE.getConfiguration().getDataSourceFactory();
		flyway.setDataSource(dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
		flyway.migrate();

		Credentials credentials = new Credentials();
		credentials.setUsername("it_tester");
		credentials.setPassword("tester2018");

		Response response = RULE.client().target(
			String.format("http://localhost:%d/api/v1/session/login", RULE.getLocalPort()))
			.request()
			.post(Entity.json(credentials));

		Map<String, NewCookie> cookies = response.getCookies();
		NewCookie cookie = cookies.get(ResumeAuthFilter.COOKIE_ACCESS_TOKEN);
		accessToken = cookie.getValue();
	}

	@AfterClass
	public static void tearDown() {
		DeleteDbFiles.execute(".", "resume", true);
	}

	@Test
	public void testUsersGetAll() {
		Response response = RULE.client().target(
			String.format("http://localhost:%d/users", RULE.getLocalPort()))
			.request()
			.cookie(ResumeAuthFilter.COOKIE_ACCESS_TOKEN, accessToken)
			.get();

		List<UserWS> userWSList = response.readEntity(List.class);
		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(userWSList.size()).isEqualTo(1);
	}
}
