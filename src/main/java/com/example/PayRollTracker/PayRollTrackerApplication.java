package com.example.PayRollTracker;

import com.example.PayRollTracker.model.Roles;
import com.example.PayRollTracker.model.UserEntity;
import com.example.PayRollTracker.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class PayRollTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayRollTrackerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(PasswordEncoder passwordEncoder, UserRepo userRepo) {
		return args -> {
			String adminUsername = "admin";
			if (!userRepo.existsByUsername(adminUsername)) {
				UserEntity admin = new UserEntity();
				admin.setUsername(adminUsername);
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setRole(Roles.ADMIN);
				userRepo.save(admin);
			}


		};
	}

}
