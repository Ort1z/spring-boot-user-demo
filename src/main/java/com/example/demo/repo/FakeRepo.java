package com.example.demo.repo;


import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeRepo implements FakeRepoInterface {
    private List<User> users = new ArrayList<>();

    @Override
    public String insertUser(long id, String name, String surname) {
        User user = new User(id, name, surname);
        users.add(user);
        return name; // Return first name as per requirements
    }

    @Override
    public String findUserById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user.getName() + " " + user.getSurname(); // Return full name
            }
        }
        return "User not found";
    }

    @Override
    public String deleteUser(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return user.getName(); // Return first name
            }
        }
        return "User not found";
    }
}