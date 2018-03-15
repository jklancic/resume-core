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

	@SqlQuery("SELECT uuid, date, description, user_uuid FROM achievement WHERE user_uuid = :user_uuid")
	List<Achievement> getAllByUser(@Bind("user_uuid") String userUuid);
	
	@SqlQuery("SELECT uuid, date, description, user_uuid FROM achievement WHERE uuid = :uuid AND user_uuid = :user_uuid")
	Achievement getByUuid(@Bind("uuid") String uuid, @Bind("user_uuid") String userUuid);

	@SqlUpdate("INSERT INTO achievement(uuid, date, description, user_uuid) VALUES (:uuid, :date, :description, :user_uuid)")
	void insert(@Bind("uuid") String uuid, @Bind("date") Date date, @Bind("description") String description, @Bind("user_uuid") String userUuid);

	@SqlUpdate("UPDATE achievement SET date = :date, description = :description WHERE uuid = :uuid")
	void update(@Bind("uuid") String uuid, @Bind("date") Date date, @Bind("description") String description);
}
