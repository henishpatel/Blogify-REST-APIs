package com.henishpatel.bloggingapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
	private List<PostDTO> content;

	private int pageNumber;

	private int pageSize;

	private int totalPages;

	private int totalElements;

	private boolean lastPage;

}
