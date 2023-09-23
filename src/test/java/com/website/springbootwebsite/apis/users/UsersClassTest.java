package com.website.springbootwebsite.apis.users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UsersClassTest {
    private static UserService service;

    @BeforeAll
    public static void createObjects(@Autowired UserRepository repository) {
        service = new UserService(repository);
    }

    @Test
    @Order(1)
    public void addUserTest() {
        long id = service.addUser("Kasra", "farrokhi.kasra@gmail.com", "1234", "1234");
        assertNotEquals(-1, id);

    }

    @Test
    public void addUserTestFailure1() {
        long id = service.addUser("Kasra", "farrokhi.kasra@gmail.com", "1234", "123");
        assertEquals(id, -1);
    }

    @Test
    @Order(2)
    public void addUserTestFailure2() {
        long id = service.addUser("Kasra", "farrokhi.kasra@gmail.com", "1234", "1234");
        assertEquals(-1, id);
    }
}
