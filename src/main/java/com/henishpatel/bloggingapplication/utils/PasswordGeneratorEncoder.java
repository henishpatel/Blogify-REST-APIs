package com.henishpatel.bloggingapplication.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		System.out.println(passwordEncoder.encode("michealpass"));
		System.out.println(passwordEncoder.encode("danielpass"));
		System.out.println(passwordEncoder.encode("sophiapass"));


	}
}
