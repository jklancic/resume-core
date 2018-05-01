package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Person;

/**
 * Row mapper for person models
 */
public class PersonSQLMapper implements RowMapper<Person> {

	private static final String UUID = "uuid";
	private static final String BIRTH_DATE = "birth_date";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String OVERVIEW = "overview";
	private static final String CONTACT_INFO_UUID = "contact_information_uuid";
	private static final String BASE_URL = "base_url";
	private static final String CREATED_BY_USER_UUID = "create_by_user_uuid";
	
	@Override
	public Person map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Person(
			rs.getString(UUID), rs.getDate(BIRTH_DATE), rs.getString(FIRST_NAME),
			rs.getString(LAST_NAME), rs.getString(OVERVIEW), rs.getString(CONTACT_INFO_UUID),
			rs.getString(BASE_URL), rs.getString(CREATED_BY_USER_UUID));
	}
}
