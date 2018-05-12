package xyz.blackmonster.resume.models.builder;

import java.sql.Date;

import xyz.blackmonster.resume.models.Education;

/**
 * Education builder
 */
public class EducationBuilder implements Builder<Education> {

	private Education education;

	public EducationBuilder() {
		education = new Education();
	}

	public EducationBuilder uuid(String uuid) {
		education.setUuid(uuid);
		return this;
	}

	public EducationBuilder date(Date date) {
		education.setDate(date);
		return this;
	}

	public EducationBuilder title(String title) {
		education.setTitle(title);
		return this;
	}

	public EducationBuilder institution(String institution) {
		education.setInstitution(institution);
		return this;
	}

	public EducationBuilder city(String city) {
		education.setCity(city);
		return this;
	}

	public EducationBuilder country(String country) {
		education.setCountry(country);
		return this;
	}

	public EducationBuilder personUuid(String personUuid) {
		education.setPersonUuid(personUuid);
		return this;
	}

	private String uuid;
	private Date date;
	private String title;
	private String institution;
	private String city;
	private String country;
	private String personUuid;

	@Override
	public Education build() {
		return education;
	}
}
