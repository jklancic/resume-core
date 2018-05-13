package xyz.blackmonster.resume.ws.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;

import xyz.blackmonster.resume.model.Achievement;
import xyz.blackmonster.resume.model.builder.AchievementBuilder;
import xyz.blackmonster.resume.ws.response.AchievementWS;

public class AchievementWSMapperTest {

	@Test
	public void testToWS() {
		Achievement achievement = new AchievementBuilder()
			.uuid("uuid")
			.date(new Date(System.currentTimeMillis()))
			.description("description")
			.build();

		AchievementWS achievementWS = AchievementWSMapper.toWS(achievement);
		validate(achievement, achievementWS);
	}

	@Test
	public void testToModel() {
		AchievementWS achievementWS = new AchievementWS();
		achievementWS.setUuid("uuid");
		achievementWS.setDate(new Date(System.currentTimeMillis()));
		achievementWS.setDescription("description");
		String personUuid = "personUuid";

		Achievement achievement = AchievementWSMapper.toModel(achievementWS, personUuid);
		validate(achievement, achievementWS);
		assertThat(achievement.getPersonUuid()).isEqualTo(personUuid);
	}

	private void validate(Achievement achievement, AchievementWS achievementWS) {
		assertThat(achievementWS.getUuid()).isEqualTo(achievement.getUuid());
		assertThat(achievementWS.getDate()).isEqualTo(achievement.getDate());
		assertThat(achievementWS.getDescription()).isEqualTo(achievement.getDescription());
	}
}
