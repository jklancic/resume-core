package xyz.blackmonster.resume.controllers.api.v1;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

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
	public Response login(Credentials credentials) throws AuthenticationException {
		String accessToken = userService.authenticateUser(credentials.getUsername(), credentials.getPassword());
		return Response
			.status(Response.Status.OK)
			.cookie(new NewCookie(ResumeAuthFilter.COOKIE_ACCESS_TOKEN, accessToken))
			.build();
	}

	@GET
	@Path("/session/logout")
	public Response logout() {
		return Response
			.status(Response.Status.OK)
			.cookie(new NewCookie(ResumeAuthFilter.COOKIE_ACCESS_TOKEN, null))
			.build();
	}
}
