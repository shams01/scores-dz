package ru.pflb.scores.service;

import ru.pflb.scores.entity.User;

public interface UserService {

    User registerUser(String name);

    User getUser(long id);
}
