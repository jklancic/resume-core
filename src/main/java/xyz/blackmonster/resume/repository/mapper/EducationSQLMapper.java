package xyz.blackmonster.resume.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.model.Education;
import xyz.blackmonster.resume.model.builder.EducationBuilder;

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
		return new EducationBuilder()
			.uuid(rs.getString(UUID))
			.date(rs.getDate(DATE))
			.title(rs.getString(TITLE))
			.institution(rs.getString(INSTITUTION))
			.city(rs.getString(CITY))
			.country(rs.getString(COUNTRY))
			.personUuid(rs.getString(PERSON_UUID))
			.build();
	}
}
