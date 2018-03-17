package xyz.blackmonster.resume.config.bean;

import javax.inject.Singleton;

import dagger.Component;
import xyz.blackmonster.resume.controllers.AchievementController;
import xyz.blackmonster.resume.security.auth.ResumeAuthenticator;

@Singleton
@Component(modules = BeanModule.class)
public interface BeanComponent {
	
	AchievementController getAchievementControler();
	ResumeAuthenticator getResumeAuthenticator();
}
