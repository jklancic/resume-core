package xyz.blackmonster.resume.config.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.service.AchievementService;
import xyz.blackmonster.resume.service.AchievementServiceImpl;
import xyz.blackmonster.resume.service.ContactInfoService;
import xyz.blackmonster.resume.service.ContactInfoServiceImpl;
import xyz.blackmonster.resume.service.EducationService;
import xyz.blackmonster.resume.service.EducationServiceImpl;
import xyz.blackmonster.resume.service.ExperienceService;
import xyz.blackmonster.resume.service.ExperienceServiceImpl;
import xyz.blackmonster.resume.service.JWTService;
import xyz.blackmonster.resume.service.JWTServiceImpl;
import xyz.blackmonster.resume.service.PersonService;
import xyz.blackmonster.resume.service.PersonServiceImpl;
import xyz.blackmonster.resume.service.SkillService;
import xyz.blackmonster.resume.service.SkillServiceImpl;
import xyz.blackmonster.resume.service.UserService;
import xyz.blackmonster.resume.service.UserServiceImpl;

@Module
public class ServiceBeanModule {

	@Singleton
	@Provides
	AchievementService provideAchievementService(AchievementServiceImpl achievementService) {
		return achievementService;
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

	@Singleton
	@Provides
	JWTService provideJWTService(JWTServiceImpl jwtService) {
		return jwtService;
	}
}
