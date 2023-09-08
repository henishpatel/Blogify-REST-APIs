package com.henishpatel.bloggingapplication.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;

	private String password;
}
