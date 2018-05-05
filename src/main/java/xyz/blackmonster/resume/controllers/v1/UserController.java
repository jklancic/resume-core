package xyz.blackmonster.resume.controllers.v1;

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

import xyz.blackmonster.resume.controllers.ApiVersioning;
import xyz.blackmonster.resume.services.UserService;
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
	public Response getAll() {
		List<UserWS> userList = userService.getAll();
		return Response.status(Response.Status.OK).entity(userList).build();
	}

	@GET
	@Path("/users/{uuid}")
	public Response getByUuid(@PathParam("uuid") String uuid) {
		UserWS user = userService.getByUuid(uuid);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	@GET
	@Path("/users/{username}")
	public Response getByUsername(@PathParam("username") String username) {
		UserWS user = userService.getByUsername(username);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	@POST
	@Path("/users")
	public Response create(UserWS userWS) {
		UserWS newUser = userService.createUser(userWS);
		return Response.status(Response.Status.CREATED).entity(newUser).build();
	}

	@PUT
	@Path("/users/{userUuid}")
	public Response update(@PathParam("userUuid") String userUuid, UserWS userWS) {
		UserWS user = userService.updateUser(userUuid, userWS);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	@DELETE
	@Path("/users/{userUuid}")
	public Response delete(@PathParam("userUuid") String userUuid) {
		userService.deleteUser(userUuid);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
