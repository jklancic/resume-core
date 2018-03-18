package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Category;

/**
 * Row mapper for category model
 */
public class CategorySQLMapper implements RowMapper<Category> {

	private static final String UUID = "uuid";
	private static final String NAME = "name";
	
	@Override
	public Category map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new Category(rs.getString(UUID), rs.getString(NAME));
	}
}
