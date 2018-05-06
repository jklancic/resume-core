package xyz.blackmonster.resume.repositories.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.models.Experience;
import xyz.blackmonster.resume.repositories.mappers.ExperienceSQLMapper;

/**
 * Experience DAO
 */
@RegisterRowMapper(ExperienceSQLMapper.class)
public interface ExperienceDAO {

	@SqlQuery("SELECT * FROM experiences WHERE person_uuid = :person_uuid")
	List<Experience> getAllByPerson(@Bind("person_uuid") String personUuid);

	@SqlQuery("SELECT * FROM experiences WHERE uuid = :uuid AND person_uuid = :personUuid")
	Optional<Experience> getByUuid(@Bind("uuid") String uuid, @Bind("personUuid") String personUuid);

	@SqlUpdate("INSERT INTO experiences(uuid, start_date, end_date, title, description, city, country, person_uuid) VALUES (:uuid, :startDate, :endDate, :title, :description, :city, :country, :personUuid)")
	void create(@BindBean Experience experience);

	@SqlUpdate("UPDATE experiences SET start_date = :startDate, end_date = :endDate, title = :title, description = :description, city = :city, country = :country WHERE uuid = :uuid AND person_uuid = :personUuid")
	void update(@BindBean Experience experience);

	@SqlUpdate("DELETE FROM experiences WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);

	@SqlUpdate("DELETE FROM experiences WHERE person_uuid = :personUuid")
	void deleteAllByPerson(@Bind("personUuid") String personUuid);
}
