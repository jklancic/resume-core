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

import xyz.blackmonster.resume.model.Education;
import xyz.blackmonster.resume.model.builder.EducationBuilder;
import xyz.blackmonster.resume.repository.dao.EducationDAO;
import xyz.blackmonster.resume.ws.response.EducationWS;

@RunWith(MockitoJUnitRunner.class)
public class EducationServiceImplTest {

	@Mock
	private EducationDAO educationDAO;

	private EducationService educationService;

	@Before
	public void setUp() {
		educationService = new EducationServiceImpl(educationDAO);
	}

	@Test
	public void testGetAllByPerson() {
		String personUuid = "personUuid";
		List<Education> educationList = new ArrayList<>(1);
		Education education = new EducationBuilder()
			.personUuid(personUuid)
			.uuid("uuid")
			.date(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		educationList.add(education);
		when(educationDAO.getAllByPerson(eq(personUuid))).thenReturn(educationList);

		List<EducationWS> educationWSList = educationService.getAllByPerson(personUuid);
		assertThat(educationWSList.size()).isEqualTo(educationList.size());
		validate(educationWSList.get(0), educationList.get(0));
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		String personUuid = "personUuid";
		Education education = new EducationBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.date(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		when(educationDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(education));

		EducationWS educationWS = educationService.getByUuid(uuid, personUuid);
		validate(educationWS, education);
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Education> captor = ArgumentCaptor.forClass(Education.class);
		String uuid = "uuid";
		String personUuid = "personUuid";
		Education education = new EducationBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.date(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		EducationWS educationWS = new EducationWS();
		educationWS.setDate(new Date(System.currentTimeMillis()));
		educationWS.setTitle("title");
		when(educationDAO.getByUuid(anyString(), eq(personUuid))).thenReturn(Optional.of(education));

		EducationWS ws = educationService.create(educationWS, personUuid);
		verify(educationDAO, times(1)).create(captor.capture());
		assertThat(captor.getValue().getUuid()).isNotBlank();
		validate(ws, education);
	}

	@Test
	public void testUpdate() {
		ArgumentCaptor<Education> captor = ArgumentCaptor.forClass(Education.class);
		String uuid = "uuid";
		String personUuid = "personUuid";
		Education education = new EducationBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.date(new Date(System.currentTimeMillis()))
			.title("title")
			.build();
		EducationWS educationWS = new EducationWS();
		educationWS.setUuid(uuid);
		educationWS.setDate(new Date(System.currentTimeMillis()));
		educationWS.setTitle("title");
		when(educationDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(education));

		EducationWS ws = educationService.update(educationWS, personUuid);
		verify(educationDAO, times(1)).update(captor.capture());
		assertThat(captor.getValue().getUuid()).isEqualTo(uuid);
		validate(ws, education);
	}

	@Test
	public void testDelete() {
		String uuid = "uuid";

		educationService.delete(uuid);
		verify(educationDAO, times(1)).delete(eq(uuid));
	}

	private void validate(EducationWS educationWS, Education education) {
		assertThat(educationWS.getUuid()).isEqualTo(education.getUuid());
		assertThat(educationWS.getDate()).isEqualTo(education.getDate());
		assertThat(educationWS.getTitle()).isEqualTo(education.getTitle());
	}
}
