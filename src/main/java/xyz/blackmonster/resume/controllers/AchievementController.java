package xyz.blackmonster.resume.controllers;

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

import io.dropwizard.auth.Auth;
import xyz.blackmonster.resume.security.model.User;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Achievement controller
 */
@Path(BaseController.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class AchievementController extends BaseController {
	
	private AchievementService achievementService;
	
	@Inject
	public AchievementController(AchievementService achievementService) {
		this.achievementService = achievementService;
	}
	
	@GET
	@Path("/user/{personUuid}/achievements")
	public List<AchievementWS> getAllByUser(@PathParam("personUuid") String personUuid) {
		return achievementService.getAllByUser(personUuid);
	}
	
	@GET
	@Path("/user/{personUuid}/achievements/{uuid}")
	public AchievementWS getByUuid(@PathParam("personUuid") String personUuid, @PathParam("uuid") String uuid) {
		return achievementService.getByUuid(uuid, personUuid);
	}
	
	@POST
	@RolesAllowed("ADMIN")
	@Path("/user/{personUuid}/achievements/")
	public AchievementWS create(@Auth User user, AchievementWS achievementWS) {
		return null;
	}
	
	@PUT
	@RolesAllowed("ADMIN")
	@Path("/user/{personUuid}/achievements/{achievementUuid}")
	public AchievementWS update(@Auth User user, AchievementWS achievementWS) {
		return null;
	}
	
	@DELETE
	@RolesAllowed("ADMIN")
	@Path("/user/{personUuid}/achievements/{achievementUuid}")
	public Response delete(AchievementWS achievementWS) {
		return null;
	}
}
