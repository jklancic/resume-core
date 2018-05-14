package xyz.blackmonster.resume.app;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import xyz.blackmonster.resume.config.app.ResumeConfiguration;
import xyz.blackmonster.resume.config.app.RuntimeConfiguration;
import xyz.blackmonster.resume.config.component.DaggerResumeComponent;
import xyz.blackmonster.resume.config.component.ResumeComponent;
import xyz.blackmonster.resume.config.module.DAOBeanModule;
import xyz.blackmonster.resume.config.module.ServiceBeanModule;
import xyz.blackmonster.resume.controllers.mapper.AuthenticationExceptionMapper;
import xyz.blackmonster.resume.controllers.mapper.JWTCreationExceptionMapper;
import xyz.blackmonster.resume.controllers.mapper.NotFoundExceptionMapper;
import xyz.blackmonster.resume.security.auth.ResumeAuthFilter;

public class ResumeApplication extends Application<ResumeConfiguration> {

	private static final String REALM = "resume";
	private static final String HEALTH_CHECK_NAME = "DB";

	private ResumeComponent resumeComponent;

	public static void main(String[] args) throws Exception {
		new ResumeApplication().run(args);
	}

	@Override
	public void run(ResumeConfiguration configuration, Environment environment) throws Exception {
		final JdbiFactory factory = new JdbiFactory();
		final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), HEALTH_CHECK_NAME);

		RuntimeConfiguration.get().setJwtSecret(configuration.getJwtSecret());

		initInjection(jdbi);
		initExceptionHandlers(environment);
		initControllers(environment);
		initAuthentication(environment);
		initCORS(environment);
	}

	private void initInjection(Jdbi jdbi) {
		resumeComponent =
			DaggerResumeComponent.builder()
				.dAOBeanModule(new DAOBeanModule(jdbi))
				.serviceBeanModule(new ServiceBeanModule())
				.build();
	}

	private void initExceptionHandlers(Environment environment) {
		environment.jersey().register(new NotFoundExceptionMapper());
		environment.jersey().register(new AuthenticationExceptionMapper());
		environment.jersey().register(new JWTCreationExceptionMapper());
	}

	private void initControllers(Environment environment) {
		environment.jersey().register(resumeComponent.getAchievementController());
		environment.jersey().register(resumeComponent.getContactInfoController());
		environment.jersey().register(resumeComponent.getEducationController());
		environment.jersey().register(resumeComponent.getExperienceController());
		environment.jersey().register(resumeComponent.getPersonController());
		environment.jersey().register(resumeComponent.getSkillController());
		environment.jersey().register(resumeComponent.getUserController());
		environment.jersey().register(resumeComponent.getSessionController());
	}

	private void initAuthentication(Environment environment) {
		ResumeAuthFilter filter = new ResumeAuthFilter(resumeComponent.getResumeAuthenticator());
		environment.jersey().register(new AuthDynamicFeature(filter));
		environment.jersey().register(RolesAllowedDynamicFeature.class);
	}

	private void initCORS(Environment environment) {
		final FilterRegistration.Dynamic cors =
			environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}
}
