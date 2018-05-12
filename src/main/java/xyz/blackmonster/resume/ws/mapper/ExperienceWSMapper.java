package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Experience;
import xyz.blackmonster.resume.models.builder.ExperienceBuilder;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

/**
 * Mapper for transforming to and from ExperienceWS
 */
public class ExperienceWSMapper {

	public static ExperienceWS toWS(Experience experience) {
		return new ExperienceWS(experience);
	}

	public static Experience toModel(ExperienceWS experienceWS, String personUuid) {
		return new ExperienceBuilder()
			.uuid(experienceWS.getUuid())
			.startDate(experienceWS.getStartDate())
			.endDate(experienceWS.getEndDate())
			.title(experienceWS.getTitle())
			.description(experienceWS.getDescription())
			.city(experienceWS.getCity())
			.country(experienceWS.getCountry())
			.build();
	}
}
