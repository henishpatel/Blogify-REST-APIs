package com.henishpatel.bloggingapplication.payload;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;

	private UserDTO user;
}
