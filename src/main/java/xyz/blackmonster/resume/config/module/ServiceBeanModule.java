package xyz.blackmonster.resume.config.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.controllers.api.v1.access.AchievementSecurity;
import xyz.blackmonster.resume.controllers.api.v1.access.AchievementSecurityImpl;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.services.AchievementServiceImpl;
import xyz.blackmonster.resume.services.ContactInfoService;
import xyz.blackmonster.resume.services.ContactInfoServiceImpl;
import xyz.blackmonster.resume.services.EducationService;
import xyz.blackmonster.resume.services.EducationServiceImpl;
import xyz.blackmonster.resume.services.ExperienceService;
import xyz.blackmonster.resume.services.ExperienceServiceImpl;
import xyz.blackmonster.resume.services.PersonService;
import xyz.blackmonster.resume.services.PersonServiceImpl;
import xyz.blackmonster.resume.services.SkillService;
import xyz.blackmonster.resume.services.SkillServiceImpl;
import xyz.blackmonster.resume.services.UserService;
import xyz.blackmonster.resume.services.UserServiceImpl;

@Module
public class ServiceBeanModule {

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

	@Singleton
	@Provides
	ContactInfoService provideContactInfoService(ContactInfoServiceImpl contactInfoService) {
		return contactInfoService;
	}

	@Singleton
	@Provides
	EducationService provideEducationService(EducationServiceImpl educationService) {
		return educationService;
	}

	@Singleton
	@Provides
	ExperienceService provideExperienceService(ExperienceServiceImpl experienceService) {
		return experienceService;
	}

	@Singleton
	@Provides
	PersonService providePersonService(PersonServiceImpl personService) {
		return personService;
	}

	@Singleton
	@Provides
	SkillService provideSkillService(SkillServiceImpl skillService) {
		return skillService;
	}

	@Singleton
	@Provides
	UserService provideUserService(UserServiceImpl userService) {
		return userService;
	}
}
