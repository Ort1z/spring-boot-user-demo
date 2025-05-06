package com.example.demo.service;

import com.example.demo.repo.FakeRepoInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private FakeRepoInterface fakeRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddUser() {
        userService.addUser("John", "Doe");
        assertThat(outContent.toString()).contains("John added");
        verify(fakeRepo).insertUser(anyLong(), eq("John"), eq("Doe"));
    }

    @Test
    public void testRemoveUser() {
        when(fakeRepo.deleteUser(1L)).thenReturn("John");
        userService.removeUser(1L);
        assertThat(outContent.toString()).contains("John removed");
        verify(fakeRepo).deleteUser(1L);
    }

    @Test
    public void testGetUser() {
        when(fakeRepo.findUserById(1L)).thenReturn("John Doe");
        userService.getUser(1L);
        assertThat(outContent.toString()).contains("hello John Doe");
        verify(fakeRepo).findUserById(1L);
    }

    @Test
    public void testRemoveUserNotFound() {
        when(fakeRepo.deleteUser(99L)).thenReturn("User not found");
        userService.removeUser(99L);
        assertThat(outContent.toString()).contains("User not found");
        verify(fakeRepo).deleteUser(99L);
    }

    @Test
    public void testGetUserNotFound() {
        when(fakeRepo.findUserById(99L)).thenReturn("User not found");
        userService.getUser(99L);
        assertThat(outContent.toString()).contains("User not found");
        verify(fakeRepo).findUserById(99L);
    }
}
