package xyz.blackmonster.resume.repositories.dao;

import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.ContactInfo;
import xyz.blackmonster.resume.repositories.mappers.ContactInfoSQLMapper;

/**
 * Contact information DAO
 */
@RegisterRowMapper(ContactInfoSQLMapper.class)
public interface ContactInfoDAO {

	@SqlQuery("SELECT * FROM contact_informations WHERE uuid = :uuid")
	Optional<ContactInfo> getByUuid(@Bind("uuid") String uuid);

	@Transaction
	@SqlUpdate("INSERT INTO contact_informations(uuid, email, phone, street, city, postal_code, country) VALUES (:uuid, :email, :phone, :street, :city, :postalCode, :country)")
	void create(@BindBean ContactInfo contactInfo);

	@Transaction
	@SqlUpdate("UPDATE contact_informations SET email = :email, phone = :phone, street = :street, city = :city, postal_code = :postalCode, country = :country WHERE uuid = :uuid")
	void update(@BindBean ContactInfo contactInfo);

	@Transaction
	@SqlUpdate("DELETE FROM contact_informations WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
