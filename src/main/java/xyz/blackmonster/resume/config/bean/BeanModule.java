package xyz.blackmonster.resume.config.bean;

import javax.inject.Singleton;

import org.jdbi.v3.core.Jdbi;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.auth.Authenticator;
import xyz.blackmonster.resume.repositories.AchievementDAO;
import xyz.blackmonster.resume.security.auth.ResumeAuthenticator;
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
	UserDAO provideUserDAO() {
		return jdbi.onDemand(UserDAO.class);
	}
	
	@Singleton
	@Provides
	AchievementService provideAchievementService(AchievementServiceImpl achievementService) {
		return achievementService;
	}
}
