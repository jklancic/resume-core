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
import xyz.blackmonster.resume.services.EducationService;
import xyz.blackmonster.resume.ws.response.EducationWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class EducationController {

	private EducationService educationService;

	@Inject
	public EducationController(EducationService educationService) {
		this.educationService = educationService;
	}

	@GET
	@Path("/{personUuid}/educations")
	public Response getAllByPerson(@PathParam("personUuid") String personUuid) {
		return Response.status(Response.Status.OK).entity(educationService.getAllByPerson(personUuid)).build();
	}

	@GET
	@Path("/{personUuid}/educations/{educationUuid}")
	public Response getByUuid(@PathParam("personUuid") String personUuid, @PathParam("educationUuid") String educationUuid) {
		return Response.status(Response.Status.OK).entity(educationService.getByUuid(educationUuid, personUuid)).build();
	}

	@POST
	@Path("/{personUuid}/educations")
	public Response create(@Auth User user, @PathParam("personUuid") String personUuid, EducationWS educationWS) {
		return Response.status(Response.Status.CREATED).entity(educationService.create(educationWS, personUuid)).build();
	}

	@PUT
	@Path("/{personUuid}/educations/{educationUuid}")
	public Response update(@Auth User user, @PathParam("personUuid") String personUuid, @PathParam("educationUuid") String educationUuid, EducationWS educationWS) {
		educationWS.setUuid(educationUuid);
		return Response.status(Response.Status.OK).entity(educationService.update(educationWS, personUuid)).build();
	}

	@DELETE
	@Path("/{personUuid}/educations/{educationUuid}")
	public Response delete(@PathParam("educationUuid") String educationUuid) {
		educationService.delete(educationUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
