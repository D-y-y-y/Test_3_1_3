package ru.kata.spring.boot_security.demo.init;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


@Component
public class Init {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);

        User adminTest = new User("adminTest", "$2a$10$jP1pL8i5ztpaEyUJwpZoduwmmMb1Jvg2lYXtMAeNPqsnjoYxACRTS", "admin@ad.min", Collections.singletonList(adminRole));
        userRepository.save(adminTest);
        User userTest = new User("userTest", "$2a$10$jP1pL8i5ztpaEyUJwpZoduwmmMb1Jvg2lYXtMAeNPqsnjoYxACRTS", "user@user.user", Collections.singletonList(userRole));
        userRepository.save(userTest);
    }
}