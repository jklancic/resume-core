package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;

import xyz.blackmonster.resume.model.Education;
import xyz.blackmonster.resume.model.builder.EducationBuilder;
import xyz.blackmonster.resume.ws.response.EducationWS;

public class EducationWSMapperTest {

	@Test
	public void testToWS() {
		Education education = new EducationBuilder()
			.uuid("uuid")
			.date(new Date(System.currentTimeMillis()))
			.title("title")
			.institution("institution")
			.city("city")
			.country("country")
			.build();

		EducationWS educationWS = EducationWSMapper.toWS(education);
		validate(education, educationWS);
	}

	@Test
	public void testToModel() {
		EducationWS educationWS = new EducationWS();
		educationWS.setUuid("uuid");
		educationWS.setDate(new Date(System.currentTimeMillis()));
		educationWS.setTitle("title");
		educationWS.setInstitution("institution");
		educationWS.setCity("city");
		educationWS.setCountry("country");

		String personUuid = "personUuid";
		Education education = EducationWSMapper.toModel(educationWS, personUuid);
		validate(education, educationWS);
		assertThat(education.getPersonUuid()).isEqualTo(personUuid);
	}

	private void validate(Education education, EducationWS educationWS) {
		assertThat(education.getUuid()).isEqualTo(educationWS.getUuid());
		assertThat(education.getDate()).isEqualTo(educationWS.getDate());
		assertThat(education.getTitle()).isEqualTo(educationWS.getTitle());
		assertThat(education.getInstitution()).isEqualTo(educationWS.getInstitution());
		assertThat(education.getCity()).isEqualTo(educationWS.getCity());
		assertThat(education.getCountry()).isEqualTo(educationWS.getCountry());
	}
}
