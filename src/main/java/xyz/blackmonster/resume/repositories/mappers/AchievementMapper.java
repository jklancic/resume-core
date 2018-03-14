package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import xyz.blackmonster.resume.models.Achievement;

public class AchievementMapper implements ResultSetMapper<Achievement> {

	private static final String UUID = "uuid";
	private static final String DATE = "date";
	private static final String DESC = "description";
	private static final String USER_UUID = "user_uuid";

	@Override
	public Achievement map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Achievement(r.getString(UUID), r.getDate(DATE), r.getString(DESC), r.getString(USER_UUID));
	}
}
