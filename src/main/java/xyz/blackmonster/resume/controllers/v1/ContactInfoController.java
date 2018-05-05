package xyz.blackmonster.resume.controllers.v1;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xyz.blackmonster.resume.controllers.ApiVersioning;
import xyz.blackmonster.resume.services.ContactInfoService;
import xyz.blackmonster.resume.ws.response.ContactInfoWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class ContactInfoController {

	private ContactInfoService contactInfoService;

	@Inject
	public ContactInfoController(ContactInfoService contactInfoService) {
		this.contactInfoService = contactInfoService;
	}

	@GET
	@Path("/{personUuid}/contact")
	public ContactInfoWS getContactInformation(@PathParam("personUuid") String personUuid) {
		return contactInfoService.getByPersonUuid(personUuid);
	}
}
