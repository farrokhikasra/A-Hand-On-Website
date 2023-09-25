package com.website.springbootwebsite.apis.users;

import com.website.springbootwebsite.security.PasswordHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> allUsers() {
        return repository.findAll();
    }

    public UserEntity findById(long id) {
        return repository.findById(id);
    }

    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<UserEntity> findAllByRole(Role role) {
        return repository.findAllByRole(role);
    }

    public long addUser(String username, String email, String password, String confirmPassword) {
        if (findByEmail(email) != null || findByUsername(username) != null) {
            return -1;
        } else if (!password.equals(confirmPassword)) {
            return -1;
        }
        String encodedPassword = PasswordHandler.getInstance().encode(password);
        UserEntity newUser = new UserEntity(username, email, encodedPassword);
        repository.save(newUser);
        return newUser.getId();
    }

}
