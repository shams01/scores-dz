package ru.pflb.scores.util;

public record Session(int userId, String sessionKey, long created) {
    public Session(int userId, String sessionKey) {
        this(userId, sessionKey, System.currentTimeMillis());
    }
}
