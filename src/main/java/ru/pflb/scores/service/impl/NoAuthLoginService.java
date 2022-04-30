package ru.pflb.scores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pflb.scores.service.LoginService;
import ru.pflb.scores.util.SessionEvictingHolder;

@Component
public class NoAuthLoginService implements LoginService {

    private final SessionEvictingHolder sessionHolder;

    @Autowired
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
