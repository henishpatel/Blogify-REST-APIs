package com.henishpatel.bloggingapplication.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {

	private Integer postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;

}
