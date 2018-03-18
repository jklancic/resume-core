package xyz.blackmonster.resume.repositories.dao;

import java.sql.Date;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.repositories.mappers.AchievementSQLMapper;

/**
 * Achievement DAO
 */
@RegisterRowMapper(AchievementSQLMapper.class)
public interface AchievementDAO {

	@SqlQuery("SELECT * FROM achievements WHERE person_uuid = :person_uuid")
	List<Achievement> getAllByPerson(@Bind("person_uuid") String personUuid);
	
	@SqlQuery("SELECT * FROM achievements WHERE uuid = :uuid AND person_uuid = :person_uuid")
	Achievement getByUuid(@Bind("uuid") String uuid, @Bind("person_uuid") String personUuid);

	@SqlUpdate("INSERT INTO achievements(uuid, date, description, person_uuid) VALUES (:uuid, :date, :description, :personUuid)")
	void insert(@BindBean Achievement achievement);

	@SqlUpdate("UPDATE achievements SET date = :date, description = :description WHERE uuid = :uuid")
	void update(@BindBean Achievement achievement);

	@SqlUpdate("DELETE FROM achievements WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
