package com.example.demo.service;

import com.example.demo.repo.FakeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements main.java.com.example.demo.service.UserService {
    private final FakeRepoInterface fakeRepo;
    private long nextId = 1; // To generate IDs

    @Autowired
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public void addUser(String name, String surname) {
        long id = nextId++;
        fakeRepo.insertUser(id, name, surname);
        System.out.println(name + " added");
    }

    @Override
    public void removeUser(long id) {
        String result = fakeRepo.deleteUser(id);
        if (!result.equals("User not found")) {
            System.out.println(result + " removed");
        } else {
            System.out.println("User not found");
        }
    }

    @Override
    public void getUser(long id) {
        String result = fakeRepo.findUserById(id);
        if (!result.equals("User not found")) {
            System.out.println("hello " + result);
        } else {
            System.out.println("User not found");
        }
    }
}