package xyz.blackmonster.resume.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import xyz.blackmonster.resume.models.Skill;
import xyz.blackmonster.resume.models.builder.SkillBuilder;

/**
 * Row mapper for skill models
 */
public class SkillSQLMapper implements RowMapper<Skill> {

	private static final String UUID = "uuid";
	private static final String MASTERY = "mastery";
	private static final String LEVEL = "level";
	private static final String PERSON_UUID = "person_uuid";
	
	@Override
	public Skill map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new SkillBuilder()
			.uuid(rs.getString(UUID))
			.mastery(rs.getString(MASTERY))
			.level(rs.getInt(LEVEL))
			.personUuid(rs.getString(PERSON_UUID))
			.build();
	}
}
