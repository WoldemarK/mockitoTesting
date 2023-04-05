package com.example.mockitotesting.service;

import com.example.mockitotesting.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final List<User> userList = new ArrayList<>();

    public List<User> getAll() {
        return userList;
    }

    public void add(User user) {
        this.userList.add(user);
    }

    public Optional<User> login(String username, String password) {
        return userList.stream()
                .filter(user -> user.getName().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }
}

