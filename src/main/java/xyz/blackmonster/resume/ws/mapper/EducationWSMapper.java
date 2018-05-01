package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Education;
import xyz.blackmonster.resume.ws.response.EducationWS;

/**
 * Mapper for transforming to and from EducationWS
 */
public class EducationWSMapper {
	
	public static EducationWS toWS(Education education) {
		return new EducationWS(
			education.getUuid(), 
			education.getDate(), 
			education.getTitle(), 
			education.getInstitution(), 
			education.getCity(), 
			education.getCountry());
	}
	
	public static Education toModel(EducationWS educationWS, String personUuid) {
		return new Education(
			educationWS.getUuid(), 
			educationWS.getDate(), 
			educationWS.getTitle(), 
			educationWS.getInstitution(), 
			educationWS.getCity(), 
			educationWS.getCountry(), 
			personUuid);
	}
}
