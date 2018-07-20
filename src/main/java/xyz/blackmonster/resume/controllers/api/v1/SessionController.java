package xyz.blackmonster.resume.controllers.api.v1;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import xyz.blackmonster.resume.controllers.api.ApiVersioning;
import xyz.blackmonster.resume.security.auth.Credentials;
import xyz.blackmonster.resume.security.auth.ResumeAuthFilter;
import xyz.blackmonster.resume.service.UserService;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class SessionController {

	private UserService userService;

	@Inject
	public SessionController(UserService userService) {
		this.userService = userService;
	}

	@POST
	@Path("/session/login")
	public Response login(@Context UriInfo uriInfo, Credentials credentials) throws AuthenticationException {
		String accessToken = userService.authenticateUser(credentials.getUsername(), credentials.getPassword());
		NewCookie cookie = createSessionCookie(uriInfo, accessToken);
		return Response
			.status(Response.Status.OK)
			.cookie(cookie)
			.build();
	}

	@POST
	@Path("/session/logout")
	public Response logout(@Context UriInfo uriInfo, @CookieParam(ResumeAuthFilter.COOKIE_ACCESS_TOKEN) String accessToken) {
		userService.deleteAccessToken(accessToken);
		NewCookie cookie = createSessionCookie(uriInfo, accessToken, 0);
		return Response
			.status(Response.Status.OK)
			.cookie(cookie)
			.build();
	}

	private NewCookie createSessionCookie(@Context UriInfo uriInfo, String accessToken) {
		return createSessionCookie(uriInfo, accessToken, NewCookie.DEFAULT_MAX_AGE);
	}

	private NewCookie createSessionCookie(@Context UriInfo uriInfo, String accessToken, int maxAge) {
		return new NewCookie(
			ResumeAuthFilter.COOKIE_ACCESS_TOKEN, accessToken,
			"/", uriInfo.getBaseUri().toString(),
			Cookie.DEFAULT_VERSION, null,
			maxAge, null,
			false, false);
	}
}
