package com.example.sprinbootweb.service;

import com.example.sprinbootweb.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void deleteById(Integer id);

    User findById(Integer id);

    List<User> findAllUsers();
}
