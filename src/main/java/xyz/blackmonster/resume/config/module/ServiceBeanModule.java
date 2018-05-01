package xyz.blackmonster.resume.config.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.controllers.access.AchievementSecurity;
import xyz.blackmonster.resume.controllers.access.AchievementSecurityImpl;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.services.AchievementServiceImpl;
import xyz.blackmonster.resume.services.CategoryService;
import xyz.blackmonster.resume.services.CategoryServiceImpl;
import xyz.blackmonster.resume.services.ContactInfoService;
import xyz.blackmonster.resume.services.ContactInfoServiceImpl;

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
	CategoryService provideCategoryService(CategoryServiceImpl categoryService) {
		return categoryService;
	}

	@Singleton
	@Provides
	ContactInfoService provideContactInfoService(ContactInfoServiceImpl contactInfoService) {
		return contactInfoService;
	}
}
