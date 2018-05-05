package xyz.blackmonster.resume.repositories.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import xyz.blackmonster.resume.models.User;
import xyz.blackmonster.resume.repositories.mappers.UserSQLMapper;

@RegisterRowMapper(UserSQLMapper.class)
public interface UserDAO {

	@SqlQuery("SELECT uuid, date, description, role FROM user")
	List<User> getAll();

	@SqlQuery("SELECT uuid, date, description, role FROM user WHERE uuid = :uuid")
	Optional<User> getByUuid(@Bind("username") String uuid);

	@SqlQuery("SELECT uuid, date, description, role FROM user WHERE username = :username")
	Optional<User> getByUsername(@Bind("username") String username);

	@Transaction
	@SqlUpdate("INSERT INTO user(uuid, username, password, role) VALUES (:uuid, :username, :password, :role)")
	void create(@Bind("uuid") String uuid, @Bind("username") String username, @Bind("username") String password, @Bind("role") int role);

	@Transaction
	@SqlUpdate("UPDATE user SET username = :username, password = :password, role = :role WHERE uuid = :uuid")
	void update(@Bind("username") String username, @Bind("username") String password, @Bind("role") int role, @Bind("uuid") String uuid);

	@Transaction
	@SqlUpdate("DELETE FROM user WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
