package com.user.userservice;

import com.user.userservice.model.ERole;
import com.user.userservice.model.Role;
import com.user.userservice.model.User;
import com.user.userservice.repository.RoleRepository;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		roleRepository.save(new Role(ERole.ROLE_GENERAL));
//		roleRepository.save(new Role(ERole.ROLE_ADMIN));
//
//		//Adding a admin
//		Set<Role> roles = new HashSet<>();
//		roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role is not Found")));
//		User userAdmin=new User("root","root");
//		userAdmin.setRoles(roles);
//		userService.createUser(userAdmin);

	}

}
