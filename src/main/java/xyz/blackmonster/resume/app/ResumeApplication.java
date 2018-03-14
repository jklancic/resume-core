package xyz.blackmonster.resume.app;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import xyz.blackmonster.resume.config.ResumeConfiguration;
import xyz.blackmonster.resume.controllers.AchievementController;
import xyz.blackmonster.resume.repositories.AchievementDAO;
import xyz.blackmonster.resume.services.AchievementService;
import xyz.blackmonster.resume.services.AchievementServiceImpl;

public class ResumeApplication extends Application<ResumeConfiguration> {

	private static final String MYSQL = "mysql";

	public static void main(String[] args) throws Exception {
		new ResumeApplication().run(args);
	}
	
	@Override
	public void run(ResumeConfiguration configuration, Environment environment) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), MYSQL);
		
		final AchievementDAO achievementDAO = jdbi.onDemand(AchievementDAO.class);
		final AchievementService achievementService = new AchievementServiceImpl(achievementDAO);
		environment.jersey().register(new AchievementController(achievementService));
	}
}
