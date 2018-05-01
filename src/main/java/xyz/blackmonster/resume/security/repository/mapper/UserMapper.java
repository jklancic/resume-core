package xyz.blackmonster.resume.security.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.security.models.Role;
import xyz.blackmonster.resume.security.models.User;

public class UserMapper implements RowMapper<User> {

	private static final String UUID = "uuid";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	
	@Override
	public User map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new User(rs.getString(UUID), rs.getString(USERNAME), rs.getString(PASSWORD), Role.toRole(rs.getInt(ROLE)));
	}
}
