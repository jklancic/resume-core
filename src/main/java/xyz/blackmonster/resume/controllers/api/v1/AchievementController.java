package xyz.blackmonster.resume.controllers.api.v1;

import java.util.List;

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

import io.dropwizard.auth.AuthenticationException;
import xyz.blackmonster.resume.controllers.api.ApiVersioning;
import xyz.blackmonster.resume.controllers.api.v1.access.AchievementSecurity;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Achievement controller
 */
@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class AchievementController {
	
	private AchievementSecurity achievementSecurity;
	
	private AchievementService achievementService;
	
	@Inject
	public AchievementController(AchievementSecurity achievementSecurity, AchievementService achievementService) {
		this.achievementSecurity = achievementSecurity;
		this.achievementService = achievementService;
	}
	
	@GET
	@Path("/person/{personUuid}/achievements")
	public Response getAllByPerson(@PathParam("personUuid") String personUuid) {
		List<AchievementWS> achievementList = achievementService.getAllByPerson(personUuid);
		return Response.status(Response.Status.OK).entity(achievementList).build();
	}
	
	@GET
	@Path("/person/{personUuid}/achievements/{uuid}")
	public Response getByUuid(@PathParam("personUuid") String personUuid, @PathParam("uuid") String uuid) {
		AchievementWS achievement = achievementService.getByUuid(uuid, personUuid);
		return Response.status(Response.Status.OK).entity(achievement).build();
	}
	
	@POST
	@Path("/person/{personUuid}/achievements/")
	@RolesAllowed({"ADMIN", "USER"})
	public Response create(@PathParam("personUuid") String personUuid, AchievementWS achievementWS) throws AuthenticationException {
		AchievementWS newAchievement = achievementService.create(achievementWS, personUuid);
		return Response.status(Response.Status.CREATED).entity(newAchievement).build();
	}
	
	@PUT
	@Path("/person/{personUuid}/achievements/{achievementUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response update(@PathParam("personUuid") String personUuid, @PathParam("achievementUuid") String achievementUuid, AchievementWS achievementWS) throws AuthenticationException {
		achievementWS.setUuid(achievementUuid);
		AchievementWS achievement = achievementService.update(achievementWS, personUuid);
		return Response.status(Response.Status.OK).entity(achievement).build();
	}
	
	@DELETE
	@Path("/person/{personUuid}/achievements/{achievementUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response delete(@PathParam("personUuid") String personUuid, @PathParam("achievementUuid") String achievementUuid) throws AuthenticationException {
		achievementService.delete(achievementUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
