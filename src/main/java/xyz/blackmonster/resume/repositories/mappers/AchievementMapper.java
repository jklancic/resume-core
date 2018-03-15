package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Achievement;

public class AchievementMapper implements RowMapper<Achievement> {

	private static final String UUID = "uuid";
	private static final String DATE = "date";
	private static final String DESC = "description";
	private static final String USER_UUID = "user_uuid";

	@Override
	public Achievement map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Achievement(rs.getString(UUID), rs.getDate(DATE), rs.getString(DESC), rs.getString(USER_UUID));
	}
}