package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Achievement;

/**
 * Row mapper for achievement models
 */
public class AchievementSQLMapper implements RowMapper<Achievement> {

	private static final String UUID = "uuid";
	private static final String DATE = "date";
	private static final String DESC = "description";
	private static final String PERSON_UUID = "person_uuid";

	@Override
	public Achievement map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Achievement(rs.getString(UUID), rs.getDate(DATE), rs.getString(DESC), rs.getString(PERSON_UUID));
	}
}
