package xyz.blackmonster.resume.controllers.api.v1;

public class UserControllerIT {

//	private String accessToken;
//
//	@Inject
//	private UserService userService;
//	@Inject
//	private PersonService personService;
//
//	@ClassRule
//	public static final DropwizardAppRule<ResumeConfiguration> RULE =
//		new DropwizardAppRule<ResumeConfiguration>(
//			ResumeApplication.class, ResourceHelpers.resourceFilePath("configuration-it.yml"));
//
//	@Before
//	public void setUp() {
//		UserWS admin = new UserWS();
//		admin.setUuid(UUID.randomUUID().toString());
//		admin.setUsername("username");
//		admin.setPassword("password");
//		admin.setRole(Role.ADMIN.name());
//		userService.createUser(admin);
//
//		Credentials credentials = new Credentials();
//		credentials.setUsername("username");
//		credentials.setPassword("password");
//
//		Response response = RULE.client().target(
//			String.format("http://localhost:%d/session/login", RULE.getLocalPort()))
//			.request()
//			.post(Entity.json(credentials));
//
//		Map<String, NewCookie> cookies = response.getCookies();
//		NewCookie cookie = cookies.get(ResumeAuthFilter.COOKIE_ACCESS_TOKEN);
//		accessToken = cookie.getValue();
//	}
//
//	@Test
//	public void testUsersGetAll() {
//		Response response = RULE.client().target(
//			String.format("http://localhost:%d/users", RULE.getLocalPort()))
//			.request()
//			.cookie(ResumeAuthFilter.COOKIE_ACCESS_TOKEN, accessToken)
//			.get();
//
//		List<UserWS> userWSList = response.readEntity(List.class);
//		assertThat(response.getStatus()).isEqualTo(200);
//		assertThat(userWSList.size()).isEqualTo(1);
//	}
}
