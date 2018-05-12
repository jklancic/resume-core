package xyz.blackmonster.resume.controllers.api.v1;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import xyz.blackmonster.resume.controllers.api.ApiVersioning;
import xyz.blackmonster.resume.service.ContactInfoService;
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
	@Path("/person/{personUuid}/contact")
	public Response getContactInformation(@PathParam("personUuid") String personUuid) {
		return Response.status(Response.Status.OK).entity(contactInfoService.getByPersonUuid(personUuid)).build();
	}

	@GET
	@Path("/person/{personUuid}/contact/{contactUuid}")
	public Response getByUuid(@PathParam("personUuid") String personUuid, @PathParam("contactUuid") String contactUuid) {
		return Response.status(Response.Status.OK).entity(contactInfoService.getByUuid(contactUuid)).build();
	}

	@PUT
	@Path("/person/{personUuid}/contact/{contactUuid}")
	@RolesAllowed({"ADMIN", "USER"})
	public Response update(@PathParam("personUuid") String personUuid, @PathParam("contactUuid") String contactUuid, ContactInfoWS contactInfoWS) {
		contactInfoWS.setUuid(contactUuid);
		return Response.status(Response.Status.OK).entity(contactInfoService.update(contactInfoWS)).build();
	}
}
