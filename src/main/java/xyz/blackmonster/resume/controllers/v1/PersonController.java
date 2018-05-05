package xyz.blackmonster.resume.controllers.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import xyz.blackmonster.resume.controllers.ApiVersioning;
import xyz.blackmonster.resume.services.PersonService;
import xyz.blackmonster.resume.ws.response.PersonWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

	private PersonService personService;

	@Inject
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GET
	@Path("/users")
	public Response getAll() {
		List<PersonWS> personList = personService.getAll();
		return Response.status(Response.Status.OK).entity(personList).build();
	}
}
