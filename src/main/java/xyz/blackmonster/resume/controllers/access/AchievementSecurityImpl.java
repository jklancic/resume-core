package xyz.blackmonster.resume.controllers.access;

import javax.inject.Inject;

import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.security.model.Role;
import xyz.blackmonster.resume.security.model.User;

public class AchievementSecurityImpl implements AchievementSecurity {
	
	private PersonDAO personDAO;
	
	@Inject
	public AchievementSecurityImpl(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}


	@Override
	public boolean canCreateAchievement(User user, String personUuid) {
		return isAdmin(user) || isOwner(user, personUuid);
	}

	@Override
	public boolean canUpdateAchievement(User user, String personUuid) {
		return isAdmin(user) || isOwner(user, personUuid);
	}

	@Override
	public boolean canDeleteAchievement(User user, String personUuid) {
		return isAdmin(user) || isOwner(user, personUuid);
	}
	
	private boolean isAdmin(User user) {
		return user.getRole().equals(Role.ADMIN);
	}
	
	private boolean isOwner(User user, String personUuid) {
		Person person = personDAO.getByUuidAndOwnerUuid(personUuid, user.getUuid());
		return person != null;
	}
}
