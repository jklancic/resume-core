package xyz.blackmonster.resume.web.views;

import io.dropwizard.views.View;

public class HomeView extends View {

	private static final String HOME_VIEW = "home.ftl";

	public HomeView() {
		super(HOME_VIEW);
		// TODO: Should call the API randomFacts and set the value that way instead of JS
	}
}
