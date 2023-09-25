package com.website.springbootwebsite.apis.users;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    void save(UserEntity user);

    List<UserEntity> findAll();

    List<UserEntity> findAllByRole(Role role);

    UserEntity findById(Long id);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

}
