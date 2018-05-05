package xyz.blackmonster.resume.repositories.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.repositories.mappers.SkillSQLMapper;

/**
 * Skill DAO
 */
@RegisterRowMapper(SkillSQLMapper.class)
public interface SkillDAO {

	@SqlQuery("SELECT * FROM skills WHERE person_uuid = :person_uuid")
	List<Skill> getAllByPerson(@Bind("person_uuid") String personUuid);

	@SqlQuery("SELECT * FROM skills WHERE uuid = :uuid AND person_uuid = :personUuid")
	Optional<Skill> getByUuid(@Bind("uuid") String uuid, @Bind("personUuid") String personUuid);

	@Transaction
	@SqlUpdate("INSERT INTO skills(uuid, mastery, level, person_uuid) VALUES (:uuid, :mastery, :level, :personUuid)")
	void create(@BindBean Skill skill);

	@Transaction
	@SqlUpdate("UPDATE skills SET mastery = :mastery, level = :level, person_uuid = :personUuid")
	void update(@BindBean Skill skill);

	@Transaction
	@SqlUpdate("DELETE FROM skills WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
