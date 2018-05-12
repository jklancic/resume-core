package xyz.blackmonster.resume.models.builder;

import java.sql.Date;

import xyz.blackmonster.resume.models.Experience;

public class ExperienceBuilder implements Builder<Experience> {

	private Experience experience;

	public ExperienceBuilder() {
		experience = new Experience();
	}

	public ExperienceBuilder uuid(String uuid) {
		experience.setUuid(uuid);
		return this;
	}

	public ExperienceBuilder startDate(Date startDate) {
		experience.setStartDate(startDate);
		return this;
	}

	public ExperienceBuilder endDate(Date endDate) {
		experience.setEndDate(endDate);
		return this;
	}

	public ExperienceBuilder title(String title) {
		experience.setTitle(title);
		return this;
	}

	public ExperienceBuilder description(String description) {
		experience.setDescription(description);
		return this;
	}

	public ExperienceBuilder city(String city) {
		experience.setCity(city);
		return this;
	}

	public ExperienceBuilder country(String country) {
		experience.setCountry(country);
		return this;
	}

	public ExperienceBuilder personUuid(String personUuid) {
		experience.setPersonUuid(personUuid);
		return this;
	}

	@Override
	public Experience build() {
		return experience;
	}
}
