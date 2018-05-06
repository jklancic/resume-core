package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class PersonUuidSQLMapper implements ColumnMapper<String> {

	@Override
	public String map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
		return r.getString(columnNumber);
	}

	@Override
	public String map(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException {
		return r.getString(columnLabel);
	}
}
