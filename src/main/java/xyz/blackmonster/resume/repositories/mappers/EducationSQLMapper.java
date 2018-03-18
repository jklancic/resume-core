package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Education;

/**
 * Row mapper for education model
 */
public class EducationSQLMapper implements RowMapper<Education> {

	private static final String UUID = "uuid";
	private static final String DATE = "date";
	private static final String TITLE = "title";
	private static final String INSTITUTION = "institution";
	private static final String CITY = "city";
	private static final String COUNTRY = "country";
	private static final String PERSON_UUID = "person_uuid";
	
	@Override
	public Education map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Education(
			rs.getString(UUID), rs.getDate(DATE), rs.getString(TITLE), 
			rs.getString(INSTITUTION), rs.getString(CITY), rs.getString(COUNTRY), rs.getString(PERSON_UUID));
	}
}
