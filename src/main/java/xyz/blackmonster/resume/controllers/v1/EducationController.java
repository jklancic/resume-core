package xyz.blackmonster.resume.controllers.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xyz.blackmonster.resume.controllers.ApiVersioning;
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
	@Path("/{personUuid}/contact")
	public List<EducationWS> getAllByPerson(@PathParam("personUuid") String personUuid) {
		return educationService.getAllByPerson(personUuid);
	}
}
