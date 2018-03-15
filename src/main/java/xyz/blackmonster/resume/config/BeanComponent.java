package xyz.blackmonster.resume.config;

import javax.inject.Singleton;

import dagger.Component;
import xyz.blackmonster.resume.controllers.AchievementController;

@Singleton
@Component(modules = BeanModule.class)
public interface BeanComponent {
	
	AchievementController getAchievementControler();
}
