package xyz.blackmonster.resume.repositories.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.repositories.mappers.PersonSQLMapper;

/**
 * Person DAO
 */
@RegisterRowMapper(PersonSQLMapper.class)
public interface PersonDAO {

	@SqlQuery("SELECT * FROM persons")
	List<Person> getAll();

	@SqlQuery("SELECT * FROM persons WHERE uuid = :uuid")
	Person getByUuid(@Bind("uuid") String uuid);

	@SqlQuery("SELECT * FROM persons WHERE uuid = :uuid AND created_by_user_uuid = :ownerUuid")
	Person getByUuidAndOwnerUuid(@Bind("uuid") String uuid, @Bind("ownerUuid") String ownerUuid);

	@SqlUpdate("INSERT INTO persons(uuid, birth_date, first_name, last_name, overview, contact_information_uuid, created_by_user_uuid) VALUES (:uuid, :birthDate, :firstName, :lastName, :overview, :contactInfoUuid, :createdByUser)")
	void create(@BindBean Person person);

	@SqlUpdate("UPDATE persons SET birth_date = :birthDate, first_name = :firstName, last_name = :lastName, overview = :overview, contact_information_uuid = :contactInfoUuid")
	void update(@BindBean Person person);

	@SqlUpdate("DELETE FROM persons WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
