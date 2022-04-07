package ru.pflb.scores.dto;

import java.util.List;
import java.util.stream.Collectors;

public record HighScores(List<Score> scores) {

    private final static String DELIMITER = ",";

    @Override
    public String toString() {
        return scores.stream()
                .map(Score::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
