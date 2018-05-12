package xyz.blackmonster.resume.repository.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.model.Skill;
import xyz.blackmonster.resume.repository.mapper.SkillSQLMapper;

/**
 * Skill DAO
 */
@RegisterRowMapper(SkillSQLMapper.class)
public interface SkillDAO {

	@SqlQuery("SELECT * FROM skills WHERE person_uuid = :person_uuid")
	List<Skill> getAllByPerson(@Bind("person_uuid") String personUuid);

	@SqlQuery("SELECT * FROM skills WHERE uuid = :uuid AND person_uuid = :personUuid")
	Optional<Skill> getByUuid(@Bind("uuid") String uuid, @Bind("personUuid") String personUuid);

	@SqlUpdate("INSERT INTO skills(uuid, mastery, level, person_uuid) VALUES (:uuid, :mastery, :level, :personUuid)")
	void create(@BindBean Skill skill);

	@SqlUpdate("UPDATE skills SET mastery = :mastery, level = :level WHERE uuid = :uuid AND person_uuid = :personUuid")
	void update(@BindBean Skill skill);

	@SqlUpdate("DELETE FROM skills WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);

	@SqlUpdate("DELETE FROM skills WHERE person_uuid = :personUuid")
	void deleteAllByPerson(@Bind("personUuid") String personUuid);
}
