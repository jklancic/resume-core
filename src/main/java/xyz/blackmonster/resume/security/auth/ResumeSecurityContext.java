package xyz.blackmonster.resume.security.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import xyz.blackmonster.resume.models.Role;

public class ResumeSecurityContext implements SecurityContext {

	private final String personUuid;

	private final ResumeAuthUser principal;

	private final SecurityContext securityContext;

	public ResumeSecurityContext(String personUuid, ResumeAuthUser principal, SecurityContext securityContext) {
		this.personUuid = personUuid;
		this.principal = principal;
		this.securityContext = securityContext;
	}

	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		if(role.equals(Role.ADMIN.name()) && role.equals(principal.getRole().name())) {
			return true;
		}
		return role.equals(Role.USER.name()) &&
			role.equals(principal.getRole().name()) &&
			principal.isUserAuthorized(personUuid);
	}

	@Override
	public boolean isSecure() {
		return securityContext.isSecure();
	}

	@Override
	public String getAuthenticationScheme() {
		return "ACCESS_TOKEN";
	}
}
