package com.website.springbootwebsite.apis.users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UsersPackageTest {
    private static UserService service;
    private final String username = "Kasra";
    private final String email = "farrokhi.kasra@gmail.com";
    private final String password = "1234";

    @BeforeAll
    public static void createObjects(@Autowired UserRepository repository) {
        service = new UserService(repository);
    }

    @Test
    @Order(1)
    public void addUserTest() {
        long id = service.addUser(username, email, password, password);
        assertNotEquals(-1, id);
    }

    @Test
    public void addUserTestFailure1() {
        long id = service.addUser(username, email, "1234", "123");
        assertEquals(-1, id);
    }

    @Test
    @Order(2)
    public void addUserTestFailure2() {
        long id = service.addUser(username, email, password, password);
        assertEquals(-1, id);
    }


    @Test
    void allUsersTest() {
        long id = service.addUser("Kasra", "farrokhi.kasra@gmail.com", "1234", "1234");
        List<UserEntity> users = service.allUsers();
        assertEquals(1, users.size());
    }

    @Test
    void findByEmailTest() {
        service.addUser(username, email, password, password);
        assertEquals(username, service.findByEmail(email).getUsername());
    }

    @Test
    void findByUsernameTest() {
        assertEquals(email, service.findByUsername(username).getEmail());
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void findByIdTest() {
        service.addUser(username, email, password, password);
        service.addUser(username + "1", email + "1", password + "1", password + "1");
        service.addUser(username + "2", email + "2", password + "2", password + "2");
        assertEquals(username, service.findById(1).getUsername());
        assertEquals(username + "1", service.findById(2).getUsername());
        assertEquals(username + "2", service.findById(3).getUsername());
    }


}
