package com.example.mockitotesting;

import com.example.mockitotesting.model.User;
import com.example.mockitotesting.service.UserService;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    @DisplayName("Перед началом работы каждого метода, и для каждого метода")
    void prepare() {
        userService = new UserService();
    }

    @Test
    @DisplayName("Проверка имеется ли пользователь с име")
    void loginSuccessIfExist() {
        User user = User.builder()
                .id(1L)
                .name("Mark")
                .password("123")
                .build();

        userService.add(user);
        Optional<User> someuser = userService.login(user.getName(), user.getPassword());
        assertTrue(someuser.isPresent());
        someuser.ifPresent(users -> assertEquals(users, user));
    }


    @Test
    @DisplayName("Получение всех записей о пользователях")
    void usersEmptyIfNoUserAdded() {
        List<User> users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    @DisplayName("Условия если пользователь вел не корректный пароль")
    void logicFailIfPasswordIfNotCorrect() {
        Optional<User> user = Optional.of(new User(1L, "Mark", "12223"));
        userService.add(user.get());
        user = userService.login(String.valueOf(user.get()), "12322");
        assertTrue(user.isEmpty());
    }
    @Test
    @DisplayName("Условия если пользователь вел не корректный имя")
    void logicFailIfUserDoesNoExist() {
        Optional<User> user = Optional.of(new User(1L, "Mark", "12223"));
        userService.add(user.get());
        user = userService.login(("NN"), "12322");
        assertTrue(user.isEmpty());
    }
    @Test
    @DisplayName("Проверка добавления пользователей")
    void userSizeIfUserAdded() {
        User mark = User.builder()
                .id(1L)
                .name("Mark")
                .password("123")
                .build();
        User sara = User.builder()
                .id(1L)
                .name("Sara")
                .password("321")
                .build();

        userService.add(mark);
        userService.add(sara);

        List<User> users = userService.getAll();
        assertEquals(2, users.size());
    }


}
