package xyz.blackmonster.resume.repository.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.repository.mapper.UserSQLMapper;

@RegisterRowMapper(UserSQLMapper.class)
public interface UserDAO {

	@SqlQuery("SELECT uuid, username, password, role FROM users")
	List<User> getAll();

	@SqlQuery("SELECT uuid, username, password, role FROM users WHERE uuid = :uuid")
	Optional<User> getByUuid(@Bind("username") String uuid);

	@SqlQuery("SELECT uuid, username, password, role FROM users WHERE username = :username")
	Optional<User> getByUsername(@Bind("username") String username);

	@SqlQuery("SELECT uuid, username, password, role FROM users WHERE access_token = :token")
	Optional<User> getByAccessToken(@Bind("token") String token);

	@SqlUpdate("INSERT INTO users (uuid, username, password, role) VALUES (:uuid, :username, :password, :role)")
	void create(@Bind("uuid") String uuid, @Bind("username") String username, @Bind("password") String password, @Bind("role") int role);

	@SqlUpdate("UPDATE users SET username = :username, password = :password, role = :role WHERE uuid = :uuid")
	void update(@Bind("username") String username, @Bind("username") String password, @Bind("role") int role, @Bind("uuid") String uuid);

	@SqlUpdate("DELETE FROM users WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);

	@SqlUpdate("UPDATE users SET access_token = :token WHERE uuid = :uuid")
	void updateAccessToken(@Bind("uuid") String uuid, @Bind("token") String token);

	@SqlUpdate("UPDATE users SET access_token = NULL WHERE access_token = :token")
	void deleteAccessToken(@Bind("token") String token);
}
