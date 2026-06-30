package org.zeroapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zeroapi.entity.User;
import org.zeroapi.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        User user = new User();

        user.setUsername("archak");

        user.setPassword(
                passwordEncoder.encode("password"));

        user.setRole("ROLE_USER");

        repository.save(user);

        User user1 = new User();
        user1.setUsername("Gojo");
        user1.setPassword(passwordEncoder.encode("almightyone"));
        user1.setRole("ROLE_ADMIN");
        repository.save(user1);


        User user2 = new User();
        user2.setUsername("Kamren");
        user2.setPassword(passwordEncoder.encode("almightyone"));
        user2.setRole("ROLE_ADMIN");
        repository.save(user2);
    }

}