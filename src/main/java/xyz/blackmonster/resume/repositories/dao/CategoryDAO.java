package xyz.blackmonster.resume.repositories.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import xyz.blackmonster.resume.models.Category;
import xyz.blackmonster.resume.repositories.mappers.CategorySQLMapper;

/**
 * Category DAO
 */
@RegisterRowMapper(CategorySQLMapper.class)
public interface CategoryDAO {

	@SqlQuery("SELECT * FROM categories")
	List<Category> getAll();
	
	@SqlQuery("SELECT * FROM categories WHERE uuid = :uuid")
	Category getByUuid(@Bind("uuid") String uuid);
	
	@SqlUpdate("INSERT INTO categories(uuid, name) VALUES (:uuid, :name)")
	void insert(@BindBean Category category);
	
	@SqlUpdate("UPDATE categories SET name = :name WHERE uuid = :uuid")
	void update(@BindBean Category category);
	
	@SqlUpdate("DELETE FROM categories WHERE uuid = :uuid")
	void delete(@Bind("uuid") String uuid);
}
