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
import xyz.blackmonster.resume.service.UserService;
import xyz.blackmonster.resume.ws.response.UserWS;

@Path(ApiVersioning.API_V1)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	private UserService userService;

	@Inject
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GET
	@Path("/users")
	@RolesAllowed("ADMIN")
	public Response getAll() {
		return Response.status(Response.Status.OK).entity(userService.getAll()).build();
	}

	@GET
	@Path("/users/{uuid}")
	@RolesAllowed("ADMIN")
	public Response getByUuid(@PathParam("uuid") String uuid) {
		return Response.status(Response.Status.OK).entity(userService.getByUuid(uuid)).build();
	}

	@GET
	@Path("/users/username/{username}")
	@RolesAllowed("ADMIN")
	public Response getByUsername(@PathParam("username") String username) {
		return Response.status(Response.Status.OK).entity(userService.getByUsername(username)).build();
	}

	@POST
	@Path("/users")
	@RolesAllowed("ADMIN")
	public Response create(UserWS userWS) {
		return Response.status(Response.Status.CREATED).entity(userService.createUser(userWS)).build();
	}

	@PUT
	@Path("/users/{userUuid}")
	@RolesAllowed("ADMIN")
	public Response update(@PathParam("userUuid") String userUuid, UserWS userWS) {
		userWS.setUuid(userUuid);
		return Response.status(Response.Status.OK).entity(userService.updateUser(userWS)).build();
	}

	@DELETE
	@Path("/users/{userUuid}")
	@RolesAllowed("ADMIN")
	public Response delete(@PathParam("userUuid") String userUuid) {
		userService.deleteUser(userUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
