package com.henishpatel.bloggingapplication.services.impl;

import com.henishpatel.bloggingapplication.entities.Category;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.payload.CategoryDTO;
import com.henishpatel.bloggingapplication.repositories.CategoryRepo;
import com.henishpatel.bloggingapplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO,Category.class);
		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory,CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryId));

		category.setCategoryName(categoryDTO.getCategoryName());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updatedCategory = categoryRepo.save(category);

		return modelMapper.map(updatedCategory,CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryId));
		return modelMapper.map(category,CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map((category) -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryId));
		categoryRepo.delete(category);
	}
}
