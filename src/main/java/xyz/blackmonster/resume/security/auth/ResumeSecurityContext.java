package xyz.blackmonster.resume.security.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import xyz.blackmonster.resume.model.Role;

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
		if (role.equals(principal.getRole().name())) {
			if (principal.getRole() == Role.ADMIN) {
				return true;
			} else if (personUuid == null) {
				return true;
			}
			return principal.getPersonList().contains(personUuid);
		}
		return false;
	}

	@Override
	public boolean isSecure() {
		return securityContext.isSecure();
	}

	@Override
	public String getAuthenticationScheme() {
		return "COOKIE_ACCESS_TOKEN";
	}
}
