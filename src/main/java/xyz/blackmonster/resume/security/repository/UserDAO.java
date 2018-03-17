package xyz.blackmonster.resume.security.repository;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import xyz.blackmonster.resume.security.model.User;
import xyz.blackmonster.resume.security.repository.mapper.UserMapper;

@RegisterRowMapper(UserMapper.class)
public interface UserDAO {

	@SqlQuery("SELECT uuid, date, description, user_uuid FROM achievement WHERE username = :username")
	User getByUsername(@Bind("username") String username);
}
