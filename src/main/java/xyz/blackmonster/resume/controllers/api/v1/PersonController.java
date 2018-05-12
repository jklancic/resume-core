package xyz.blackmonster.resume.controllers.api.v1;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import xyz.blackmonster.resume.controllers.api.ApiVersioning;
import xyz.blackmonster.resume.security.auth.ResumeAuthFilter;
import xyz.blackmonster.resume.service.PersonService;
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
	@Path("/persons/test/hello")
	public Response hello() {
		return Response.status(Response.Status.OK).entity("Hello").build();
	}

	@GET
	@Path("/persons")
	@RolesAllowed("ADMIN")
	public Response getAll() {
		return Response.status(Response.Status.OK).entity(personService.getAll()).build();
	}

	@GET
	@Path("/persons/{personUuid}")
	public Response getByUuid(@PathParam("personUuid") String personUuid) {
		return Response.status(Response.Status.OK).entity(personService.getByUuid(personUuid)).build();
	}

	@GET
	@Path("/persons/retrieve")
	public Response retrieveUuid(@HeaderParam("Origin") String origin) {
		return Response.status(Response.Status.OK).entity(personService.getUuid(origin)).build();
	}

	@PUT
	@Path("/persons/{personUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response update(@PathParam("personUuid") String personUuid, PersonWS personWS) {
		personWS.setUuid(personUuid);
		return Response.status(Response.Status.OK).entity(personService.update(personWS)).build();
	}

	@POST
	@Path("/persons/admin")
	@RolesAllowed("ADMIN")
	public Response create(@CookieParam(ResumeAuthFilter.COOKIE_ACCESS_TOKEN) String accessToken, PersonWS personWS) {
		return Response.status(Response.Status.CREATED).entity(personService.create(personWS, accessToken)).build();
	}

	@PUT
	@Path("/persons/{personUuid}/admin")
	@RolesAllowed("ADMIN")
	public Response updateAll(@CookieParam(ResumeAuthFilter.COOKIE_ACCESS_TOKEN) String accessToken, @PathParam("personUuid") String personUuid, PersonWS personWS) {
		personWS.setUuid(personUuid);
		return Response.status(Response.Status.OK).entity(personService.updateAll(personWS, accessToken)).build();
	}

	@DELETE
	@Path("/persons/{personUuid}/admin")
	@RolesAllowed("ADMIN")
	public Response delete(@PathParam("personUuid") String personUuid) {
		personService.delete(personUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
