package com.henishpatel.bloggingapplication;

import com.henishpatel.bloggingapplication.config.AppConstants;
import com.henishpatel.bloggingapplication.entities.Role;
import com.henishpatel.bloggingapplication.repositories.RoleRepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@OpenAPIDefinition(
		info = @Info(
				title = "Blogify REST-APIs",
				description = "Blofigy app made using Spring Boot - APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Henish Patel",
						email = "henish134@gmail.com",
						url = "https://github.com/henishpatel"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/henishpatel"
				)
		)
)
@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	};

	@Override
	public void run(String... args) throws Exception {

		try {

			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role> roles = List.of(role, role1);

			List<Role> result = this.roleRepo.saveAll(roles);



//			result.forEach(r -> {
//				System.out.println(r.getName());
//			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
