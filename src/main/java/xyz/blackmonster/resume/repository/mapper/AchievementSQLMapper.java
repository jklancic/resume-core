package xyz.blackmonster.resume.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.model.Achievement;
import xyz.blackmonster.resume.model.builder.AchievementBuilder;

/**
 * Row mapper for achievement model
 */
public class AchievementSQLMapper implements RowMapper<Achievement> {

	private static final String UUID = "uuid";
	private static final String DATE = "date";
	private static final String DESCRIPTION = "description";
	private static final String PERSON_UUID = "person_uuid";

	@Override
	public Achievement map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new AchievementBuilder()
			.uuid(rs.getString(UUID))
			.date(rs.getDate(DATE))
			.description(rs.getString(DESCRIPTION))
			.personUuid(rs.getString(PERSON_UUID))
			.build();
	}
}
