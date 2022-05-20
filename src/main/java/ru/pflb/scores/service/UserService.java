package ru.pflb.scores.service;

import ru.pflb.scores.entity.User;

public interface UserService {

    long registerUser(String name);

    User getUser(long id);
}
