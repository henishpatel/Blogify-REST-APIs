package com.henishpatel.bloggingapplication.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {

	private Integer postId;

	@NotEmpty
	@Size(min = 4, message = "Title must be min of 4 characters !!")
	private String title;

	@NotEmpty
	@Size(min = 10, message = "Content must be min of 10 characters !!")
	private String content;

	@NotEmpty
	private String imageName;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;

	private Set<CommentDTO> comments=new HashSet<>();

}
