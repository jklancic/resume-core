package xyz.blackmonster.resume.repository.dao;

import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.model.ContactInfo;
import xyz.blackmonster.resume.repository.mapper.ContactInfoSQLMapper;

/**
 * Contact information DAO
 */
@RegisterRowMapper(ContactInfoSQLMapper.class)
public interface ContactInfoDAO {

	@SqlQuery("SELECT * FROM contact_informations WHERE uuid = :uuid")
	Optional<ContactInfo> getByUuid(@Bind("uuid") String uuid);

	@SqlQuery("SELECT * FROM contact_informations INNER JOIN persons ON contact_informations.uuid = persons.contact_information_uuid WHERE persons.uuid = :uuid")
	Optional<ContactInfo> getByPersonUuid(@Bind("uuid") String uuid);

	@SqlUpdate("INSERT INTO contact_informations(uuid, email, phone, street, city, postal_code, country) VALUES (:uuid, :email, :phone, :street, :city, :postalCode, :country)")
	void create(@BindBean ContactInfo contactInfo);

	@SqlUpdate("UPDATE contact_informations SET email = :email, phone = :phone, street = :street, city = :city, postal_code = :postalCode, country = :country WHERE uuid = :uuid")
	void update(@BindBean ContactInfo contactInfo);

	@SqlUpdate("DELETE FROM contact_informations WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
