package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.blackmonster.resume.model.Achievement;
import xyz.blackmonster.resume.model.builder.AchievementBuilder;
import xyz.blackmonster.resume.repository.dao.AchievementDAO;
import xyz.blackmonster.resume.ws.response.AchievementWS;

@RunWith(MockitoJUnitRunner.class)
public class AchievementServiceImplTest {

	@Mock
	private AchievementDAO achievementDAO;

	private AchievementService achievementService;

	@Before
	public void setUp() {
		achievementService = new AchievementServiceImpl(achievementDAO);
	}

	@Test
	public void testGetAllByPerson() {
		String personUuid = "personUuid";
		List<Achievement> achievementList = new ArrayList<>(1);
		Achievement achievement = new AchievementBuilder()
			.personUuid(personUuid)
			.uuid("uuid")
			.date(new Date(System.currentTimeMillis()))
			.description("description")
			.build();
		achievementList.add(achievement);
		when(achievementDAO.getAllByPerson(eq(personUuid))).thenReturn(achievementList);

		List<AchievementWS> achievementWSList = achievementService.getAllByPerson(personUuid);
		assertThat(achievementWSList.size()).isEqualTo(achievementList.size());
		validate(achievementWSList.get(0), achievementList.get(0));
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		String personUuid = "personUuid";
		Achievement achievement = new AchievementBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.date(new Date(System.currentTimeMillis()))
			.description("description")
			.build();
		when(achievementDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(achievement));

		AchievementWS achievementWS = achievementService.getByUuid(uuid, personUuid);
		validate(achievementWS, achievement);
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Achievement> captor = ArgumentCaptor.forClass(Achievement.class);
		String personUuid = "personUuid";
		Achievement achievement = new AchievementBuilder()
			.personUuid(personUuid)
			.uuid("uuid")
			.date(new Date(System.currentTimeMillis()))
			.description("description")
			.build();
		AchievementWS achievementWS = new AchievementWS();
		achievementWS.setDate(new Date(System.currentTimeMillis()));
		achievementWS.setDescription("description");
		when(achievementDAO.getByUuid(anyString(), eq(personUuid))).thenReturn(Optional.of(achievement));

		AchievementWS ws = achievementService.create(achievementWS, personUuid);
		verify(achievementDAO, times(1)).create(captor.capture());
		assertThat(captor.getValue().getUuid()).isNotBlank();
		validate(ws, achievement);
	}

	@Test
	public void testUpdate() {
		ArgumentCaptor<Achievement> captor = ArgumentCaptor.forClass(Achievement.class);
		String personUuid = "personUuid";
		String uuid = "uuid";
		Achievement achievement = new AchievementBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.date(new Date(System.currentTimeMillis()))
			.description("description")
			.build();
		AchievementWS achievementWS = new AchievementWS();
		achievementWS.setUuid("uuid");
		achievementWS.setDate(new Date(System.currentTimeMillis()));
		achievementWS.setDescription("description");
		when(achievementDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(achievement));

		AchievementWS ws = achievementService.update(achievementWS, personUuid);
		verify(achievementDAO, times(1)).update(captor.capture());
		assertThat(captor.getValue().getUuid()).isEqualTo(uuid);
		validate(ws, achievement);
	}

	@Test
	public void testDelete() {
		String uuid = "uuid";

		achievementService.delete(uuid);
		verify(achievementDAO, times(1)).delete(eq(uuid));
	}

	private void validate(AchievementWS achievementWS, Achievement achievement) {
		assertThat(achievementWS.getUuid()).isEqualTo(achievement.getUuid());
		assertThat(achievementWS.getDate()).isEqualTo(achievement.getDate());
		assertThat(achievementWS.getDescription()).isEqualTo(achievement.getDescription());
	}
}
