package xyz.blackmonster.resume.security.auth;

import java.util.List;

import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;

public class ResumeAuthUser extends User {

	private List<String> personList;

	public ResumeAuthUser(String uuid, String username, String password, Role role, List<String> personList) {
		super(uuid, username, password, role);
		this.personList = personList;
	}

	public boolean isUserAuthorized(String parentUuid) {
		if(parentUuid == null) {
			return true;
		}

		return personList.contains(parentUuid);
	}
}