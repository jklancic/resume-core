package xyz.blackmonster.resume.controllers.views;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeViewController {

	@GET
	@RolesAllowed("ADMIN")
	public Response showHome() {
		return Response.status(Response.Status.OK).build();
	}
}
