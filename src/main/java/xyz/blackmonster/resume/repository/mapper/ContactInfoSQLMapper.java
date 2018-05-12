package xyz.blackmonster.resume.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.model.builder.ContactInfoBuilder;

/**
 * Row mapper for contact information model
 */
public class ContactInfoSQLMapper implements RowMapper<ContactInfo> {

	private static final String UUID = "UUID";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String STREET = "street";
	private static final String CITY = "city";
	private static final String POSTAL_CODE = "postal_code";
	private static final String COUNTRY = "country";
	
	@Override
	public ContactInfo map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new ContactInfoBuilder()
			.uuid(rs.getString(UUID))
			.email(rs.getString(EMAIL))
			.phone(rs.getString(PHONE))
			.street(rs.getString(STREET))
			.city(rs.getString(CITY))
			.postalCode(rs.getString(POSTAL_CODE))
			.country(rs.getString(COUNTRY))
			.build();
	}
}
