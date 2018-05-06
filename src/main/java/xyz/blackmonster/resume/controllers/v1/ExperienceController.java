package xyz.blackmonster.resume.controllers.v1;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import xyz.blackmonster.resume.controllers.ApiVersioning;
import xyz.blackmonster.resume.models.User;
import xyz.blackmonster.resume.services.ExperienceService;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class ExperienceController {

	private ExperienceService experienceService;

	@Inject
	public ExperienceController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	@GET
	@Path("/{personUuid}/experiences")
	public Response getAllByPerson(@PathParam("personUuid") String personUuid) {
		return Response.status(Response.Status.OK).entity(experienceService.getAllByPerson(personUuid)).build();
	}

	@GET
	@Path("/{personUuid}/experiences/{experienceUuid}")
	public Response getByUuid(@PathParam("personUuid") String personUuid, @PathParam("experienceUuid") String experienceUuid) {
		return Response.status(Response.Status.OK).entity(experienceService.getByUuid(experienceUuid, personUuid)).build();
	}

	@POST
	@Path("/{personUuid}/experiences")
	public Response create(@Auth User user, @PathParam("personUuid") String personUuid, ExperienceWS experienceWS) {
		return Response.status(Response.Status.CREATED).entity(experienceService.create(experienceWS, personUuid)).build();
	}

	@PUT
	@Path("/{personUuid}/experiences/{experienceUuid}")
	public Response update(@Auth User user, @PathParam("personUuid") String personUuid, @PathParam("experienceUuid") String experienceUuid, ExperienceWS experienceWS) {
		experienceWS.setUuid(experienceUuid);
		return Response.status(Response.Status.OK).entity(experienceService.update(experienceWS, personUuid)).build();
	}

	@DELETE
	@Path("/{personUuid}/experiences/{experienceUuid}")
	public Response delete(@PathParam("experienceUuid") String experienceUuid) {
		experienceService.delete(experienceUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
