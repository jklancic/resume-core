package xyz.blackmonster.resume.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import xyz.blackmonster.resume.model.Skill;
import xyz.blackmonster.resume.model.builder.SkillBuilder;
import xyz.blackmonster.resume.repository.dao.SkillDAO;
import xyz.blackmonster.resume.ws.response.SkillWS;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {

	@Mock
	private SkillDAO skillDAO;

	private SkillServiceImpl skillService;

	@Before
	public void setUp() {
		skillService = new SkillServiceImpl(skillDAO);
	}

	@Test
	public void testGetAllByPerson() {
		String personUuid = "personUuid";
		Skill skill = new SkillBuilder()
			.personUuid(personUuid)
			.uuid("uuid")
			.mastery("mastery")
			.level(5)
			.build();
		List<Skill> skillList = new ArrayList<>(1);
		skillList.add(skill);
		when(skillDAO.getAllByPerson(eq(personUuid))).thenReturn(skillList);

		List<SkillWS> skillWSList = skillService.getAllByPerson(personUuid);
		assertThat(skillWSList.size()).isEqualTo(skillList.size());
		validate(skillWSList.get(0), skillList.get(0));
	}

	@Test
	public void testGetByUuid() {
		String uuid = "uuid";
		String personUuid = "personUuid";
		Skill skill = new SkillBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.mastery("mastery")
			.level(5)
			.build();
		when(skillDAO.getByUuid(eq(uuid), eq(personUuid))).thenReturn(Optional.of(skill));

		SkillWS skillWS = skillService.getByUuid(uuid, personUuid);
		validate(skillWS, skill);
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Skill> captor = ArgumentCaptor.forClass(Skill.class);
		SkillWS skillWS = new SkillWS();
		skillWS.setMastery("mastery");
		skillWS.setLevel(5);
		String uuid = "uuid";
		String personUuid = "personUuid";
		Skill skill = new SkillBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.mastery("mastery")
			.level(5)
			.build();
		when(skillDAO.getByUuid(anyString(), anyString())).thenReturn(Optional.of(skill));

		SkillWS created = skillService.create(skillWS, personUuid);
		verify(skillDAO, times(1)).create(captor.capture());
		assertThat(captor.getValue()).isNotNull();
		assertThat(captor.getValue().getUuid()).isNotBlank();
		assertThat(captor.getValue().getMastery()).isEqualTo(skillWS.getMastery());
		assertThat(captor.getValue().getLevel()).isEqualTo(skillWS.getLevel());
		verify(skillDAO, times(1)).getByUuid(anyString(), anyString());
		validate(skillWS, created);
	}

	@Test
	public void testUpdate() {
		ArgumentCaptor<Skill> captor = ArgumentCaptor.forClass(Skill.class);
		String uuid = "uuid";
		String personUuid = "personUuid";
		SkillWS skillWS = new SkillWS();
		skillWS.setMastery("mastery");
		skillWS.setLevel(5);
		skillWS.setUuid(uuid);
		Skill skill = new SkillBuilder()
			.personUuid(personUuid)
			.uuid(uuid)
			.mastery("mastery")
			.level(5)
			.build();
		when(skillDAO.getByUuid(anyString(), anyString())).thenReturn(Optional.of(skill));

		SkillWS created = skillService.update(skillWS, personUuid);
		verify(skillDAO, times(1)).update(captor.capture());
		assertThat(captor.getValue()).isNotNull();
		assertThat(captor.getValue().getUuid()).isNotBlank();
		assertThat(captor.getValue().getMastery()).isEqualTo(skillWS.getMastery());
		assertThat(captor.getValue().getLevel()).isEqualTo(skillWS.getLevel());
		verify(skillDAO, times(1)).getByUuid(anyString(), anyString());
		validate(skillWS, created);
	}

	@Test
	public void testDelete() {
		String uuid = "uuid";
		doNothing().when(skillDAO).delete(eq(uuid));

		skillService.delete(uuid);
		verify(skillDAO, times(1)).delete(eq(uuid));
	}

	private void validate(SkillWS skillWS, Skill skill) {
		assertThat(skillWS.getUuid()).isEqualTo(skill.getUuid());
		assertThat(skillWS.getMastery()).isEqualTo(skill.getMastery());
		assertThat(skillWS.getLevel()).isEqualTo(skill.getLevel());
	}

	private void validate(SkillWS skillWSA, SkillWS skillWSB) {
		assertThat(skillWSA.getMastery()).isEqualTo(skillWSB.getMastery());
		assertThat(skillWSA.getLevel()).isEqualTo(skillWSB.getLevel());
	}
}
