package xyz.blackmonster.resume.controllers.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xyz.blackmonster.resume.controllers.ApiVersioning;
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
	public List<ExperienceWS> getAllByPerson(@PathParam("personUuid") String personUuid) {
		return experienceService.getAllByPerson(personUuid);
	}
}
