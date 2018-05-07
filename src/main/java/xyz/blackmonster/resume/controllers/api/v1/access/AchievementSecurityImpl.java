package xyz.blackmonster.resume.controllers.api.v1.access;

import java.util.Optional;

import javax.inject.Inject;

import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.models.Role;
import xyz.blackmonster.resume.models.User;

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
		Optional<Person> optionalPerson = personDAO.getByUuidAndOwnerUuid(personUuid, user.getUuid());
		return optionalPerson.isPresent();
	}
}
