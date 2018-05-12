package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.model.Education;
import xyz.blackmonster.resume.model.builder.EducationBuilder;
import xyz.blackmonster.resume.ws.response.EducationWS;

/**
 * Mapper for transforming to and from EducationWS
 */
public class EducationWSMapper {
	
	public static EducationWS toWS(Education education) {
		return new EducationWS(education);
	}
	
	public static Education toModel(EducationWS educationWS, String personUuid) {
		return new EducationBuilder()
			.uuid(educationWS.getUuid())
			.date(educationWS.getDate())
			.title(educationWS.getTitle())
			.institution(educationWS.getInstitution())
			.city(educationWS.getCity())
			.country(educationWS.getCountry())
			.personUuid(personUuid)
			.build();
	}
}
