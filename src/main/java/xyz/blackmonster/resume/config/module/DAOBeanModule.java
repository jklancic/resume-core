package xyz.blackmonster.resume.config.module;

import javax.inject.Singleton;

import org.jdbi.v3.core.Jdbi;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.repositories.dao.AchievementDAO;
import xyz.blackmonster.resume.repositories.dao.ContactInfoDAO;
import xyz.blackmonster.resume.repositories.dao.EducationDAO;
import xyz.blackmonster.resume.repositories.dao.ExperienceDAO;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.repositories.dao.SkillDAO;
import xyz.blackmonster.resume.repositories.dao.UserDAO;

@Module
public class DAOBeanModule {

	private Jdbi jdbi;

	public DAOBeanModule(Jdbi jdbi) {
		this.jdbi = jdbi;
	}

	@Singleton
	@Provides
	AchievementDAO provideAchievementDAO() {
		return jdbi.onDemand(AchievementDAO.class);
	}

	@Singleton
	@Provides
	ContactInfoDAO provideContactInfoDAO() {
		return jdbi.onDemand(ContactInfoDAO.class);
	}

	@Singleton
	@Provides
	EducationDAO provideEducationDAO() {
		return jdbi.onDemand(EducationDAO.class);
	}

	@Singleton
	@Provides
	ExperienceDAO provideExperienceDAO() {
		return jdbi.onDemand(ExperienceDAO.class);
	}

	@Singleton
	@Provides
	PersonDAO providePersonDAO() {
		return jdbi.onDemand(PersonDAO.class);
	}

	@Singleton
	@Provides
	SkillDAO provideSkillDAO() {
		return jdbi.onDemand(SkillDAO.class);
	}

	@Singleton
	@Provides
	UserDAO provideUserDAO() {
		return jdbi.onDemand(UserDAO.class);
	}
}
