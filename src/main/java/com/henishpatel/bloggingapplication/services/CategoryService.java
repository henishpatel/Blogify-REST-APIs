package com.henishpatel.bloggingapplication.services;

import com.henishpatel.bloggingapplication.payload.CategoryDTO;
import com.henishpatel.bloggingapplication.payload.UserDTO;

import java.util.List;

public interface CategoryService {
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	CategoryDTO getCategoryById(Integer categoryId);
	List<CategoryDTO> getAllCategory();
	void deleteCategory(Integer userId);
}
