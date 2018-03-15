package xyz.blackmonster.resume.app;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import xyz.blackmonster.resume.config.BeanComponent;
import xyz.blackmonster.resume.config.BeanModule;
import xyz.blackmonster.resume.config.DaggerBeanComponent;
import xyz.blackmonster.resume.config.ResumeConfiguration;

public class ResumeApplication extends Application<ResumeConfiguration> {

	private static final String MYSQL = "mysql";
	
	private BeanComponent beanComponent;

	public static void main(String[] args) throws Exception {
		new ResumeApplication().run(args);
	}
	
	@Override
	public void run(ResumeConfiguration configuration, Environment environment) throws Exception {
		final JdbiFactory factory = new JdbiFactory();
		final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), MYSQL);
		
		beanComponent = DaggerBeanComponent.builder().beanModule(new BeanModule(jdbi)).build();
		
		environment.jersey().register(beanComponent.getAchievementControler());
	}
}
