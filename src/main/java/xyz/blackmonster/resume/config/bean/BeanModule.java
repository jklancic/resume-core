package xyz.blackmonster.resume.config.bean;

import javax.inject.Singleton;

import org.jdbi.v3.core.Jdbi;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.controllers.access.AchievementSecurity;
import xyz.blackmonster.resume.controllers.access.AchievementSecurityImpl;
import xyz.blackmonster.resume.models.Education;
import xyz.blackmonster.resume.repositories.dao.AchievementDAO;
import xyz.blackmonster.resume.repositories.dao.CategoryDAO;
import xyz.blackmonster.resume.repositories.dao.EducationDAO;
import xyz.blackmonster.resume.repositories.dao.ExperienceDAO;
import xyz.blackmonster.resume.repositories.dao.PersonDAO;
import xyz.blackmonster.resume.repositories.dao.SkillDAO;
import xyz.blackmonster.resume.security.repository.UserDAO;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.services.AchievementServiceImpl;

@Module
public class BeanModule {
	
	private Jdbi jdbi;
	
	public BeanModule(Jdbi jdbi) {
		this.jdbi = jdbi;
	}
	
	@Singleton	
	@Provides
	AchievementDAO provideAchievementDAO() {
		return jdbi.onDemand(AchievementDAO.class);
	}

	@Singleton
	@Provides
	CategoryDAO provideCategoryDAO() {
		return jdbi.onDemand(CategoryDAO.class);
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
	
	@Singleton
	@Provides
	AchievementService provideAchievementService(AchievementServiceImpl achievementService) {
		return achievementService;
	}
	
	@Singleton
	@Provides
	AchievementSecurity provideAchievementSecurity(AchievementSecurityImpl achievementSecurity) {
		return achievementSecurity;
	}
}
