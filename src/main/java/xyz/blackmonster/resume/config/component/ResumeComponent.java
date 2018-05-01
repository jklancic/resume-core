package xyz.blackmonster.resume.config.component;

import javax.inject.Singleton;

import dagger.Component;
import xyz.blackmonster.resume.config.module.DAOBeanModule;
import xyz.blackmonster.resume.config.module.ServiceBeanModule;
import xyz.blackmonster.resume.controllers.AchievementController;
import xyz.blackmonster.resume.security.auth.ResumeAuthenticator;

@Singleton
@Component(modules = {DAOBeanModule.class, ServiceBeanModule.class})
public interface ResumeComponent {
	
	AchievementController getAchievementController();
	ResumeAuthenticator getResumeAuthenticator();
}
