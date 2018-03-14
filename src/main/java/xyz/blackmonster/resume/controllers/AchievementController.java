package xyz.blackmonster.resume.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.ws.AchievementWS;
import xyz.blackmonster.resume.ws.mapper.AchievementWSMapper;

/**
 * Achievement controller
 */
@Path(BaseController.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class AchievementController extends BaseController {
	
	private AchievementService achievementService;
	
	public AchievementController(AchievementService achievementService) {
		this.achievementService = achievementService;
	}
	
	@GET
	@Path("/user/{userUuid}/achievements")
	public List<AchievementWS> getAllByUser(@PathParam("userUuid") String userUuid) {
		return achievementService.getAllByUser(userUuid).stream().map(AchievementWSMapper::convert).collect(Collectors.toList());
	}
	
	@GET
	@Path("/user/{userUuid}/achievements/{uuid}")
	public AchievementWS getByUuid(@PathParam("userUuid") String userUuid, @PathParam("uuid") String uuid) {
		return AchievementWSMapper.convert(achievementService.getByUuid(uuid, userUuid));
	}

	@GET
	@Path("test")
	public String hello() {
		return "Hello Stranger!";
	}
}
