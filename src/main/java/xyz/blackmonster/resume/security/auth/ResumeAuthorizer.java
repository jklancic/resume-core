package xyz.blackmonster.resume.security.auth;

import io.dropwizard.auth.Authorizer;
import xyz.blackmonster.resume.security.model.User;

/**
 * Authorizer logic to determine user authorization for accessing resources.
 */
public class ResumeAuthorizer implements Authorizer<User> {
	
	@Override
	public boolean authorize(User user, String role) {
		return user.getRole() != null && user.getRole().name().equals(role);
	}
}
