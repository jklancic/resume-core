package xyz.blackmonster.resume.web.views;

import io.dropwizard.views.View;

/**
 * Home view
 */
public class HomeView extends View {

	private static final String HOME_VIEW = "home.ftl";

	private String chuckFact;

	public HomeView(String chuckFact) {
		super(HOME_VIEW);
		this.chuckFact = chuckFact;
	}

	public String getChuckFact() {
		return chuckFact;
	}
}
