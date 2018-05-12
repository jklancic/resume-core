package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Person;
import xyz.blackmonster.resume.models.builder.PersonBuilder;

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
	private static final String LINKEDIN_URL = "linkedin_url";
	private static final String GITHUB_URL = "github_url";
	private static final String FACEBOOK_URL = "facebook_url";
	private static final String TWITTER_URL = "twitter_url";
	private static final String CREATED_BY_USER_UUID = "create_by_user_uuid";
	
	@Override
	public Person map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new PersonBuilder()
			.uuid(rs.getString(UUID))
			.birthDate(rs.getDate(BIRTH_DATE))
			.firstName(rs.getString(FIRST_NAME))
			.lastName(rs.getString(LAST_NAME))
			.overview(rs.getString(OVERVIEW))
			.contactInfoUuid(rs.getString(CONTACT_INFO_UUID))
			.baseUrl(rs.getString(BASE_URL))
			.linkedInUrl(rs.getString(LINKEDIN_URL))
			.githubUrl(rs.getString(GITHUB_URL))
			.facebookUrl(rs.getString(FACEBOOK_URL))
			.twitterUrl(rs.getString(TWITTER_URL))
			.createdByUser(rs.getString(CREATED_BY_USER_UUID))
			.build();
	}
}
