package xyz.blackmonster.resume.repository.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.model.Person;
import xyz.blackmonster.resume.repository.mapper.PersonSQLMapper;
import xyz.blackmonster.resume.repository.mapper.PersonUuidSQLMapper;

/**
 * Person DAO
 */
@RegisterRowMapper(PersonSQLMapper.class)
@RegisterColumnMapper(PersonUuidSQLMapper.class)
public interface PersonDAO {

	@SqlQuery("SELECT * FROM persons")
	List<Person> getAll();

	@SqlQuery("SELECT uuid FROM persons WHERE created_by_user_uuid = :ownerUuid")
	List<String> getAllPersonUuidByOwnerUuid(@Bind("ownerUuid") String ownerUuid);

	@SqlQuery("SELECT * FROM persons WHERE uuid = :uuid")
	Optional<Person> getByUuid(@Bind("uuid") String uuid);

	@SqlQuery("SELECT * FROM persons WHERE uuid = :uuid AND created_by_user_uuid = :ownerUuid")
	Optional<Person> getByUuidAndOwnerUuid(@Bind("uuid") String uuid, @Bind("ownerUuid") String ownerUuid);

	@SqlQuery("SELECT uuid FROM persons WHERE base_url = :baseUrl")
	Optional<String> getUuidByBaseUrl(@Bind("baseUrl") String baseUrl);

	@SqlUpdate("INSERT INTO persons(uuid, birth_date, first_name, last_name, overview, contact_information_uuid, base_url, created_by_user_uuid) VALUES (:uuid, :birthDate, :firstName, :lastName, :overview, :contactInfoUuid, :baseUrl, :createdByUser)")
	void create(@BindBean Person person);

	@SqlUpdate("UPDATE persons SET birth_date = :birthDate, first_name = :firstName, last_name = :lastName, overview = :overview, contact_information_uuid = :contactInfoUuid, base_url = :baseUrl, created_by_user_uuid = :createdByUser WHERE uuid = :uuid")
	void updateAll(@BindBean Person person);

	@SqlUpdate("UPDATE persons SET birth_date = :birthDate, first_name = :firstName, last_name = :lastName, overview = :overview, contact_information_uuid = :contactInfoUuid WHERE uuid = :uuid")
	void update(@BindBean Person person);

	@SqlUpdate("DELETE FROM persons WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
