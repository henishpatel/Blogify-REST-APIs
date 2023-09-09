package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.CategoryDTO;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO createCategoryDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategoryDTO, HttpStatus.CREATED);
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@PutMapping("/{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer categoryId){
		CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategoryDTO, HttpStatus.OK);
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@DeleteMapping("/{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),HttpStatus.OK);
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@GetMapping("/{categoryId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}

}
