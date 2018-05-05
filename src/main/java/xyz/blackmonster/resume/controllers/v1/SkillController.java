package xyz.blackmonster.resume.controllers.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xyz.blackmonster.resume.controllers.ApiVersioning;
import xyz.blackmonster.resume.services.SkillService;
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
	@Path("/{personUuid}/skills")
	public List<SkillWS> getAllByPerson(@PathParam("personUuid") String personUuid) {
		return skillService.getAllByPerson(personUuid);
	}
}
