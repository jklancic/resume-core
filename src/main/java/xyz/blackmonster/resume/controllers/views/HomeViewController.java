package xyz.blackmonster.resume.controllers.views;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import xyz.blackmonster.resume.web.views.HomeView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeViewController {

	public HomeViewController() {
	}

	@GET
	public Response redirectToHome() {
		URI uri = UriBuilder.fromUri("/home").build();
		return Response.temporaryRedirect(uri).build();
	}

	@GET
	@Path("/home")
	public HomeView showHome() {
		// TODO: Add API call
		return new HomeView();
	}
}
