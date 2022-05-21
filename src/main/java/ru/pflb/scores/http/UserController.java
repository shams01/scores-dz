package ru.pflb.scores.http;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pflb.scores.entity.User;
import ru.pflb.scores.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/user/{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PostMapping(path = "/user")
    public User getUser(@RequestBody User user) {
        return userService.registerUser(user.getName());
    }
}
