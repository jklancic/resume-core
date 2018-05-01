package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Experience;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

/**
 * Mapper for transforming to and from ExperienceWS
 */
public class ExperienceWSMapper {

	public static ExperienceWS toWS(Experience experience) {
		return new ExperienceWS(
			experience.getUuid(),
			experience.getStartDate(),
			experience.getEndDate(),
			experience.getTitle(),
			experience.getDescription(),
			experience.getCity(),
			experience.getCountry());
	}

	public static Experience toModel(ExperienceWS experienceWS, String personUuid) {
		return new Experience(
			experienceWS.getUuid(),
			experienceWS.getStartDate(),
			experienceWS.getEndDate(),
			experienceWS.getTitle(),
			experienceWS.getDescription(),
			experienceWS.getCity(),
			experienceWS.getCountry(),
			personUuid);
	}
}
