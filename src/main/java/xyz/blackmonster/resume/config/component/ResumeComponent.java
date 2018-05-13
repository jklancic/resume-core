package xyz.blackmonster.resume.config.component;

import javax.inject.Singleton;

import dagger.Component;
import xyz.blackmonster.resume.config.module.DAOBeanModule;
import xyz.blackmonster.resume.config.module.ServiceBeanModule;
import xyz.blackmonster.resume.controllers.api.v1.AchievementController;
import xyz.blackmonster.resume.controllers.api.v1.ContactInfoController;
import xyz.blackmonster.resume.controllers.api.v1.EducationController;
import xyz.blackmonster.resume.controllers.api.v1.ExperienceController;
import xyz.blackmonster.resume.controllers.api.v1.PersonController;
import xyz.blackmonster.resume.controllers.api.v1.SessionController;
import xyz.blackmonster.resume.controllers.api.v1.SkillController;
import xyz.blackmonster.resume.controllers.api.v1.UserController;
import xyz.blackmonster.resume.security.auth.ResumeAuthenticator;

@Singleton
@Component(modules = {DAOBeanModule.class, ServiceBeanModule.class})
public interface ResumeComponent {
	
	AchievementController getAchievementController();
	ContactInfoController getContactInfoController();
	EducationController getEducationController();
	ExperienceController getExperienceController();
	PersonController getPersonController();
	SkillController getSkillController();
	UserController getUserController();
	SessionController getSessionController();

	ResumeAuthenticator getResumeAuthenticator();
}
