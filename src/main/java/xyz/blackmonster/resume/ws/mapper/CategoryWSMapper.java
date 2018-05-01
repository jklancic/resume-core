package xyz.blackmonster.resume.ws.mapper;

import xyz.blackmonster.resume.models.Category;
import xyz.blackmonster.resume.ws.response.CategoryWS;

/**
 * Mapper for transforming to and from CategoryWS
 */
public class CategoryWSMapper {

	public static CategoryWS toWS(Category category) {
		return new CategoryWS(category.getUuid(), category.getName());
	}

	public static Category toModel(CategoryWS categoryWS) {
		return new Category(categoryWS.getUuid(), categoryWS.getName());
	}
}
