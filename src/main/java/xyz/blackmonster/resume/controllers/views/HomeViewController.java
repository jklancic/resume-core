package xyz.blackmonster.resume.controllers.views;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import xyz.blackmonster.resume.services.HttpService;
import xyz.blackmonster.resume.web.views.HomeView;
import xyz.blackmonster.resume.ws.response.ChuckFactWS;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeViewController {

	private HttpService httpService;

	@Inject
	public HomeViewController(HttpService httpService) {
		this.httpService = httpService;
	}

	@GET
	public Response redirectToHome() {
		URI uri = UriBuilder.fromUri("/home").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@Path("/home")
	public HomeView showHome() {
		Optional<Object> optionalFact =
			httpService.makeRequest(HttpMethod.GET.toString(), "https://api.chucknorris.io/jokes/random", null, ChuckFactWS.class);
		if(optionalFact.isPresent()) {
			ChuckFactWS chuckFactWS = (ChuckFactWS) optionalFact.get();
			return new HomeView(chuckFactWS.getValue());
		}
		return new HomeView("No joke today :(");
	}
}
