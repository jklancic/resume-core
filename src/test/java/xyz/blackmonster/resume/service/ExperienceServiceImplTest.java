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

import xyz.blackmonster.resume.model.Experience;
import xyz.blackmonster.resume.model.builder.ExperienceBuilder;
import xyz.blackmonster.resume.repository.dao.ExperienceDAO;
import xyz.blackmonster.resume.ws.response.ExperienceWS;

@RunWith(MockitoJUnitRunner.class)
public class ExperienceServiceImplTest {

	@Mock
	private ExperienceDAO experienceDAO;

	private ExperienceService experienceService;

	@Before
	public void setUp() {
		experienceService = new ExperienceServiceImpl(experienceDAO);
	}

	@Test
	public void testGetAllByPerson() {
		String personUuid = "personUuid";
		List<Experience> experienceList = new ArrayList<>(1);
		Experience experience = new ExperienceBuilder()
			.personUuid(personUuid)
			.uuid("uuid")
			.startDate(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		experienceList.add(experience);
		when(experienceDAO.getAllByPerson(eq(personUuid))).thenReturn(experienceList);

		List<ExperienceWS> experienceWSList = experienceService.getAllByPerson(personUuid);
		assertThat(experienceWSList.size()).isEqualTo(experienceList.size());
		validate(experienceWSList.get(0), experienceList.get(0));
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		String personUuid = "personUuid";
		Experience experience = new ExperienceBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.startDate(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		when(experienceDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(experience));

		ExperienceWS experienceWS = experienceService.getByUuid(uuid, personUuid);
		validate(experienceWS, experience);
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Experience> captor = ArgumentCaptor.forClass(Experience.class);
		String uuid = "uuid";
		String personUuid = "personUuid";
		Experience experience = new ExperienceBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.startDate(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		ExperienceWS experienceWS = new ExperienceWS();
		experienceWS.setStartDate(new Date(System.currentTimeMillis()));
		experienceWS.setTitle("title");
		when(experienceDAO.getByUuid(anyString(), eq(personUuid))).thenReturn(Optional.of(experience));

		ExperienceWS ws = experienceService.create(experienceWS, personUuid);
		verify(experienceDAO, times(1)).create(captor.capture());
		assertThat(captor.getValue().getUuid()).isNotBlank();
		validate(ws, experience);
	}

	@Test
	public void testUpdate() {
		ArgumentCaptor<Experience> captor = ArgumentCaptor.forClass(Experience.class);
		String uuid = "uuid";
		String personUuid = "personUuid";
		Experience experience = new ExperienceBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.startDate(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		ExperienceWS experienceWS = new ExperienceWS();
		experienceWS.setUuid(uuid);
		experienceWS.setStartDate(new Date(System.currentTimeMillis()));
		experienceWS.setTitle("title");
		when(experienceDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(experience));

		ExperienceWS ws = experienceService.update(experienceWS, personUuid);
		verify(experienceDAO, times(1)).update(captor.capture());
		assertThat(captor.getValue().getUuid()).isEqualTo(uuid);
		validate(ws, experience);
	}

	@Test
	public void testDelete() {
		String uuid = "uuid";

		experienceService.delete(uuid);
		verify(experienceDAO, times(1)).delete(eq(uuid));
	}

	private void validate(ExperienceWS experienceWS, Experience experience) {
		assertThat(experienceWS.getUuid()).isEqualTo(experience.getUuid());
		assertThat(experienceWS.getStartDate()).isEqualTo(experience.getStartDate());
		assertThat(experienceWS.getTitle()).isEqualTo(experience.getTitle());
	}
}
