package com.henishpatel.bloggingapplication.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {


	private Integer categoryId;

	@NotBlank
	@Size(min = 4,message = "Min size of category name is 4")
	private String categoryName;

	@NotBlank
	@Size(min = 10, message = "min size of cateogry desc is 10")
	private String categoryDescription;
}
