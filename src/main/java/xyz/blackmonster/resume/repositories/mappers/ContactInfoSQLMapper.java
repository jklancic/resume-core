package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.ContactInfo;

/**
 * Row mapper for contact information models
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
		return new ContactInfo(
			rs.getString(UUID), rs.getString(EMAIL), rs.getString(PHONE), 
			rs.getString(STREET), rs.getString(CITY), rs.getString(POSTAL_CODE), rs.getString(COUNTRY));
	}
}
