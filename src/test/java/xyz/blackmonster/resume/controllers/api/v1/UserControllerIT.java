package xyz.blackmonster.resume.controllers.api.v1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import xyz.blackmonster.resume.app.ResumeApplication;
import xyz.blackmonster.resume.config.app.ResumeConfiguration;
import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.security.auth.Credentials;
import xyz.blackmonster.resume.security.auth.ResumeAuthFilter;
import xyz.blackmonster.resume.service.PersonService;
import xyz.blackmonster.resume.service.UserService;
import xyz.blackmonster.resume.ws.response.UserWS;

public class UserControllerIT {

	private String accessToken;

	@Inject
	private UserService userService;
	@Inject
	private PersonService personService;

	@ClassRule
	public static final DropwizardAppRule<ResumeConfiguration> RULE =
		new DropwizardAppRule<ResumeConfiguration>(
			ResumeApplication.class, ResourceHelpers.resourceFilePath("configuration-it.yml"));

	@Before
	public void setUp() {
		UserWS admin = new UserWS();
		admin.setUuid(UUID.randomUUID().toString());
		admin.setUsername("username");
		admin.setPassword("password");
		admin.setRole(Role.ADMIN.name());
		userService.createUser(admin);

		Credentials credentials = new Credentials();
		credentials.setUsername("username");
		credentials.setPassword("password");

		Response response = RULE.client().target(
			String.format("http://localhost:%d/session/login", RULE.getLocalPort()))
			.request()
			.post(Entity.json(credentials));

		Map<String, NewCookie> cookies = response.getCookies();
		NewCookie cookie = cookies.get(ResumeAuthFilter.COOKIE_ACCESS_TOKEN);
		accessToken = cookie.getValue();
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
