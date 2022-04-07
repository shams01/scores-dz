package ru.pflb.scores.dto;

public record Score(int userId, int score) {

    private static final String EQUALS = "=";

    @Override
    public String toString() {
        return userId + EQUALS + score;
    }
}
