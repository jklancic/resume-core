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
	@SqlUpdate("INSERT INTO skills(uuid, birth_date, first_name, last_name, overview, contact_information_uuid, created_by_user_uuid) VALUES (:uuid, :birthDate, :firstName, :lastName, :overview, :contactInfoUuid, :createdByUser)")
	void create(@BindBean Skill skill);

	@Transaction
	@SqlUpdate("UPDATE skills SET birth_date = :birthDate, first_name = :firstName, last_name = :lastName, overview = :overview, contact_information_uuid = :contactInfoUuid")
	void update(@BindBean Skill skill);

	@Transaction
	@SqlUpdate("DELETE FROM skills WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
