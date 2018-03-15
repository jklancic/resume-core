package xyz.blackmonster.resume.config;

import javax.inject.Singleton;

import org.jdbi.v3.core.Jdbi;

import dagger.Module;
import dagger.Provides;
import xyz.blackmonster.resume.repositories.AchievementDAO;
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
	AchievementService provideAchievementService(AchievementServiceImpl achievementService) {
		return achievementService;
	}
}
