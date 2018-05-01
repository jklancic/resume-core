package xyz.blackmonster.resume.services;

import java.util.List;

import xyz.blackmonster.resume.ws.response.CategoryWS;

/**
 * Category service interface
 */
public interface CategoryService {

	/**
	 * Returns all categories.
	 * @return
	 */
	List<CategoryWS> getAll();

	/**
	 * Returns category for specific category uuid.
	 * @param uuid
	 * @return
	 */
	CategoryWS getByUuid(String uuid);
}
