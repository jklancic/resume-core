package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;

import xyz.blackmonster.resume.model.Experience;
import xyz.blackmonster.resume.model.builder.ExperienceBuilder;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

public class ExperienceWSMapperTest {

	@Test
	public void testToWS() {
		Experience experience = new ExperienceBuilder()
			.uuid("uuid")
			.startDate(new Date(System.currentTimeMillis()))
			.endDate(new Date(System.currentTimeMillis()))
			.title("title")
			.description("description")
			.city("city")
			.country("country")
			.build();

		ExperienceWS experienceWS = ExperienceWSMapper.toWS(experience);
		validate(experience, experienceWS);
	}

	@Test
	public void testToModel() {
		ExperienceWS experienceWS = new ExperienceWS();
		experienceWS.setUuid("uuid");
		experienceWS.setStartDate(new Date(System.currentTimeMillis()));
		experienceWS.setEndDate(new Date(System.currentTimeMillis()));
		experienceWS.setTitle("title");
		experienceWS.setDescription("description");
		experienceWS.setCity("city");
		experienceWS.setCountry("country");

		String personUuid = "personUuid";
		Experience experience = ExperienceWSMapper.toModel(experienceWS, personUuid);
		validate(experience, experienceWS);
		assertThat(experience.getPersonUuid()).isEqualTo(personUuid);
	}

	private void validate(Experience experience, ExperienceWS experienceWS) {
		assertThat(experienceWS.getUuid()).isEqualTo(experience.getUuid());
		assertThat(experienceWS.getStartDate()).isEqualTo(experience.getStartDate());
		assertThat(experienceWS.getEndDate()).isEqualTo(experience.getEndDate());
		assertThat(experienceWS.getTitle()).isEqualTo(experience.getTitle());
		assertThat(experienceWS.getDescription()).isEqualTo(experience.getDescription());
		assertThat(experienceWS.getCity()).isEqualTo(experience.getCity());
		assertThat(experienceWS.getCountry()).isEqualTo(experience.getCountry());
	}
}
