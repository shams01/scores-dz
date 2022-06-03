package ru.pflb.scores.service;

import org.springframework.stereotype.Service;


public interface LoginService {

    String login(int userId);

    int getUserId(String sessionKey);
}
