package xyz.blackmonster.resume.controllers.api.v1;

import javax.annotation.security.RolesAllowed;
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

import xyz.blackmonster.resume.controllers.api.ApiVersioning;
import xyz.blackmonster.resume.service.SkillService;
import xyz.blackmonster.resume.ws.response.SkillWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class SkillController {

	private SkillService skillService;

	@Inject
	public SkillController(SkillService skillService) {
		this.skillService = skillService;
	}

	@GET
	@Path("/person/{personUuid}/skills")
	public Response getAllByPerson(@PathParam("personUuid") String personUuid) {
		return Response.status(Response.Status.OK).entity(skillService.getAllByPerson(personUuid)).build();
	}

	@GET
	@Path("/person/{personUuid}/skills/{skillUuid}")
	public Response getAllByUuid(@PathParam("personUuid") String personUuid, @PathParam("skillUuid") String skillUuid) {
		return Response.status(Response.Status.OK).entity(skillService.getByUuid(skillUuid, personUuid)).build();
	}

	@POST
	@Path("/person/{personUuid}/skills")
	@RolesAllowed({"ADMIN", "USER"})
	public Response create(@PathParam("personUuid") String personUuid, SkillWS skillWS) {
		return Response.status(Response.Status.CREATED).entity(skillService.create(skillWS, personUuid)).build();
	}

	@PUT
	@Path("/person/{personUuid}/skills/{skillUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response update(@PathParam("personUuid") String personUuid, @PathParam("skillUuid") String skillUuid, SkillWS skillWS) {
		skillWS.setUuid(skillUuid);
		return Response.status(Response.Status.CREATED).entity(skillService.create(skillWS, personUuid)).build();
	}

	@DELETE
	@Path("/person/{personUuid}/skills/{skillUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response delete(@PathParam("personUuid") String personUuid, @PathParam("skillUuid") String skillUuid) {
		skillService.delete(skillUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
