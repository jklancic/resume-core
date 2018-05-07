package xyz.blackmonster.resume.security.auth;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthenticationException;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class ResumeAuthFilter extends AuthFilter {

	private static final String ACCESS_TOKEN = "access_token";

	private ResumeAuthenticator resumeAuthenticator;

	public ResumeAuthFilter(ResumeAuthenticator resumeAuthenticator) {
		this.resumeAuthenticator = resumeAuthenticator;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		CustomCredentials credentials = getCredentials(requestContext);
		Optional<ResumeAuthUser> optionalUser;
		try {
			optionalUser = resumeAuthenticator.authenticate(credentials);
		} catch (AuthenticationException e) {
			throw new WebApplicationException("Unable to validate credentials.", Response.Status.UNAUTHORIZED);
		}

		if (!optionalUser.isPresent()) {
			throw new WebApplicationException("Credentials not valid", Response.Status.UNAUTHORIZED);
		}

		String personUuid = parsePersonUuid(requestContext);
		SecurityContext securityContext = new ResumeSecurityContext(personUuid, optionalUser.get(), requestContext.getSecurityContext());
		requestContext.setSecurityContext(securityContext);
	}

	private CustomCredentials getCredentials(ContainerRequestContext requestContext) {
		try {
			String rawToken = requestContext.getCookies().get(ACCESS_TOKEN).getValue();
			return new CustomCredentials(rawToken);
		} catch (Exception e) {
			throw new WebApplicationException("Unable to retrieve credentials.", Response.Status.UNAUTHORIZED);
		}
	}

	private String parsePersonUuid(ContainerRequestContext requestContext) {
		try {
			return requestContext.getUriInfo().getPathParameters().getFirst("personUuid");
		} catch (Exception e) {
			throw new WebApplicationException("No person UUID in path.", Response.Status.FORBIDDEN);
		}
	}
}
