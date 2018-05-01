package xyz.blackmonster.resume.repositories.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.Education;
import xyz.blackmonster.resume.repositories.mappers.EducationSQLMapper;

/**
 * Education DAO
 */
@RegisterRowMapper(EducationSQLMapper.class)
public interface EducationDAO {
	
	@SqlQuery("SELECT * FROM educations WHERE person_uuid = :personUuid")
	List<Education> getAllByPerson(@Bind("personUuid") String personUuid);
	
	@SqlQuery("SELECT * FROM educations WHERE uuid = :uuid AND person_uuid = :personUuid")
	Optional<Education> getByUuid(@Bind("uuid") String uuid, @Bind("personUuid") String personUuid);
	
	@Transaction
	@SqlUpdate("INSERT INTO educations(uuid, date, title, institution, city, country, person_uuid) VALUES (:uuid, :date, :title, :institution, :city, :country, :personUuid)")
	void create(@BindBean Education education);

	@Transaction
	@SqlUpdate("UPDATE educations SET date = :date, title = :title, institution = :institution, city = :city, country = :country")
	void update(@BindBean Education education);

	@Transaction
	@SqlUpdate("DELETE FROM educations WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
