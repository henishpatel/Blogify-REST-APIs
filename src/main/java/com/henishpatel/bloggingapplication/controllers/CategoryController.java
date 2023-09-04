package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.CategoryDTO;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO createCategoryDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategoryDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer categoryId){
		CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategoryDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}
}
