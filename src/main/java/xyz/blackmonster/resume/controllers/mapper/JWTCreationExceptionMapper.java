package xyz.blackmonster.resume.controllers.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.auth0.jwt.exceptions.JWTCreationException;

/**
 * JWT creation exception handler
 */
public class JWTCreationExceptionMapper implements ExceptionMapper<JWTCreationException> {
	@Override
	public Response toResponse(JWTCreationException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
	}
}
