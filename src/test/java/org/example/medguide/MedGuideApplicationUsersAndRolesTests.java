package org.example.medguide;

import org.example.medguide.model.Role;
import org.example.medguide.model.User;
import org.example.medguide.repository.RoleRepository;
import org.example.medguide.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class MedGuideApplicationUsersAndRolesTests {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    public void testEncodePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }

    @Test
    public void testCreateRole() {
        if (roleRepository.findByName("Admin") == null) {
            Role roleAdmin = new Role();
            roleAdmin.setName("Admin");
            roleAdmin.setDescription("Manage everything");
            Role savedRole = roleRepository.save(roleAdmin);
            assertThat(savedRole.getId()).isGreaterThan(0);
        }
    }

    @Test
    public void testCreateNewUserWithOneRole() {
        User user = new User();
        Role role = new Role();
        if (roleRepository.findByName("Admin") == null) {
            role.setName("Admin");
            role.setDescription("Manage everything");
            roleRepository.save(role);
        } else {
            role = roleRepository.findByName("Admin");
        }
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("12345");
        user.setEmail("john@gmail.com");
        user.setRoles(Set.of(role));
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User user = new User();
        Role roleAdmin = new Role();
        Role rolePhysician = new Role();
        if (roleRepository.findByName("Admin") == null) {
            roleAdmin.setName("Admin");
            roleAdmin.setDescription("Manage everything");
            roleRepository.save(roleAdmin);
        } else {
            roleAdmin = roleRepository.findByName("Admin");
        }
        if (roleRepository.findByName("Physician") == null) {
            rolePhysician.setName("Physician");
            rolePhysician.setDescription("Prescribe medications");
            roleRepository.save(rolePhysician);
        } else {
            rolePhysician = roleRepository.findByName("Physician");
        }
        user.setFirstName("Charles");
        user.setLastName("Oliveira");
        user.setPassword("12345");
        user.setEmail("charles@gmail.com");
        user.setRoles(Set.of(roleAdmin, rolePhysician));
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithAlreadyRegisteredEmail() {
        User user1 = new User();
        user1.setEmail("mary@gmail.com");
        user1.setFirstName("Mary");
        user1.setLastName("Shelly");
        user1.setPassword(encoder.encode("12345"));
        userRepository.save(user1);
        User user2 = new User();
        user2.setEmail("mary@gmail.com");
        user2.setFirstName("Mary-Ann");
        user2.setLastName("Jones");
        user2.setPassword(encoder.encode("qwerty"));
        assertThatThrownBy(() -> userRepository.save(user2)).isInstanceOf(DataIntegrityViolationException.class);
        User savedUser = userRepository.getUserByEmail("mary@gmail.com");
        assertThat(savedUser.getFirstName()).isEqualTo("Mary");
        assertThat(savedUser.getLastName()).isEqualTo("Shelly");
    }

    @Test
    public void testDeleteUser() {
        User user = userRepository.getUserByEmail("charles@gmail.com");
        if (user != null) {
            userRepository.delete(user);
        } else {
            System.out.println("User not found!");
        }
        assertThat(userRepository.getUserByEmail("charles@gmail.com")).isNull();
    }


}
