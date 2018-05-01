package xyz.blackmonster.resume.controllers;

import java.util.List;

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
import io.dropwizard.auth.AuthenticationException;
import xyz.blackmonster.resume.controllers.access.AchievementSecurity;
import xyz.blackmonster.resume.security.models.User;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.ws.response.AchievementWS;

/**
 * Achievement controller
 */
@Path(BaseController.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class AchievementController extends BaseController {
	
	private AchievementSecurity achievementSecurity;
	
	private AchievementService achievementService;
	
	@Inject
	public AchievementController(AchievementSecurity achievementSecurity, AchievementService achievementService) {
		this.achievementSecurity = achievementSecurity;
		this.achievementService = achievementService;
	}
	
	@GET
	@Path("/{personUuid}/achievements")
	public List<AchievementWS> getAllByUser(@PathParam("personUuid") String personUuid) {
		return achievementService.getAllByPerson(personUuid);
	}
	
	@GET
	@Path("/{personUuid}/achievements/{uuid}")
	public AchievementWS getByUuid(@PathParam("personUuid") String personUuid, @PathParam("uuid") String uuid) {
		return achievementService.getByUuid(uuid, personUuid);
	}
	
	@POST
	@Path("/{personUuid}/achievements/")
	public AchievementWS create(@Auth User user, @PathParam("personUuid") String personUuid, AchievementWS achievementWS) throws AuthenticationException {
		if(!achievementSecurity.canCreateAchievement(user, personUuid)) {
			throw new AuthenticationException("User not authorized.");
		}
		return null;
	}
	
	@PUT
	@Path("/{personUuid}/achievements/{achievementUuid}")
	public AchievementWS update(@Auth User user, @PathParam("personUuid") String personUuid, AchievementWS achievementWS) throws AuthenticationException {
		if(!achievementSecurity.canUpdateAchievement(user, personUuid)) {
			throw new AuthenticationException("User not authorized.");
		}
		return null;
	}
	
	@DELETE
	@Path("/{personUuid}/achievements/{achievementUuid}")
	public Response delete(@Auth User user, @PathParam("personUuid") String personUuid) throws AuthenticationException {
		if(!achievementSecurity.canDeleteAchievement(user, personUuid)) {
			throw new AuthenticationException("User not authorized.");
		}
		return null;
	}
}
