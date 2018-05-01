package xyz.blackmonster.resume.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import xyz.blackmonster.resume.models.Category;
import xyz.blackmonster.resume.repositories.dao.CategoryDAO;
import xyz.blackmonster.resume.ws.mapper.CategoryWSMapper;
import xyz.blackmonster.resume.ws.response.CategoryWS;

/**
 * Category service
 */
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDAO categoryDAO;
	
	@Inject
	public CategoryServiceImpl(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	@Override
	public List<CategoryWS> getAll() {
		return categoryDAO.getAll().stream().map(CategoryWSMapper::toWS).collect(Collectors.toList());
	}

	@Override
	public CategoryWS getByUuid(String uuid) {
		Optional<Category> optionalCategory = categoryDAO.getByUuid(uuid);
		if(!optionalCategory.isPresent()) {
			throw new NotFoundException(
				String.format("Could not find any category with uuid=%s", uuid));
		}
		return CategoryWSMapper.toWS(optionalCategory.get());
	}
}
