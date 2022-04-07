package ru.pflb.scores.service.impl;

import ru.pflb.scores.service.LoginService;
import ru.pflb.scores.util.SessionEvictingHolder;

public class NoAuthLoginService implements LoginService {

    private final SessionEvictingHolder sessionHolder;

    public NoAuthLoginService(SessionEvictingHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }

    @Override
    public int getUserId(String sessionKey) {
        return sessionHolder.getUserBySession(sessionKey);
    }

    @Override
    public String login(int userId) {
        return sessionHolder.createSession(userId);
    }
}
