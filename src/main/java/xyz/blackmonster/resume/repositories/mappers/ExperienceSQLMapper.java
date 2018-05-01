package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Experience;

/**
 * Row mapper for experience models
 */
public class ExperienceSQLMapper implements RowMapper<Experience> {

	private static final String UUID = "uuid";
	private static final String START_DATE = "start_date";
	private static final String END_DATE = "end_date";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String CITY = "city";
	private static final String COUNTRY = "country";
	private static final String PERSON_UUID = "person_uuid";
	
	@Override
	public Experience map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Experience(
			rs.getString(UUID), rs.getDate(START_DATE), rs.getDate(END_DATE), rs.getString(TITLE),
			rs.getString(DESCRIPTION), rs.getString(CITY), rs.getString(COUNTRY), rs.getString(PERSON_UUID));
	}
}
