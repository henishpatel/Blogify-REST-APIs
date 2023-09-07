package com.henishpatel.bloggingapplication.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {

	private int postId;

	@NotBlank
	@Size(min = 4,message = "Min size of comment content is 4")
	private String content;

}
