package xyz.blackmonster.resume.repositories;

import java.sql.Date;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.models.Achievement;
import xyz.blackmonster.resume.repositories.mappers.AchievementMapper;

@RegisterRowMapper(AchievementMapper.class)
public interface AchievementDAO {

	@SqlQuery("SELECT uuid, date, description, person_uuid FROM achievement WHERE person_uuid = :person_uuid")
	List<Achievement> getAllByUser(@Bind("person_uuid") String personUuid);
	
	@SqlQuery("SELECT uuid, date, description, person_uuid FROM achievement WHERE uuid = :uuid AND person_uuid = :person_uuid")
	Achievement getByUuid(@Bind("uuid") String uuid, @Bind("person_uuid") String personUuid);

	@SqlUpdate("INSERT INTO achievement(uuid, date, description, person_uuid) VALUES (:uuid, :date, :description, :person_uuid)")
	void insert(@Bind("uuid") String uuid, @Bind("date") Date date, @Bind("description") String description, @Bind("person_uuid") String personUuid);

	@SqlUpdate("UPDATE achievement SET date = :date, description = :description WHERE uuid = :uuid")
	void update(@Bind("uuid") String uuid, @Bind("date") Date date, @Bind("description") String description);

	@SqlUpdate("DELETE FROM achievement WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
