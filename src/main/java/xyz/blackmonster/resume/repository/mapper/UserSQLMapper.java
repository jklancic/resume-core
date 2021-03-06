package xyz.blackmonster.resume.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.model.Role;
import xyz.blackmonster.resume.model.User;
import xyz.blackmonster.resume.model.builder.UserBuilder;

public class UserSQLMapper implements RowMapper<User> {

	private static final String UUID = "uuid";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	
	@Override
	public User map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new UserBuilder()
			.uuid(rs.getString(UUID))
			.username(rs.getString(USERNAME))
			.password(rs.getString(PASSWORD))
			.role(Role.toRole(rs.getInt(ROLE)))
			.build();
	}
}
