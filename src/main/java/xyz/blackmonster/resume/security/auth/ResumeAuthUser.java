package xyz.blackmonster.resume.security.auth;

import java.util.List;

import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;

public class ResumeAuthUser extends User {

	private List<String> personList;

	public ResumeAuthUser(String uuid, String username, String password, Role role, List<String> personList) {
		super(uuid, username, password, role);
		this.personList = personList;
	}

	public List<String> getPersonList() {
		return personList;
	}
}
