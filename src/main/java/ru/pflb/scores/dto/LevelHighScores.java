package ru.pflb.scores.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LevelHighScores {

    private final static int DEFAULT_SCORES_LIMIT = 15;

    private final TreeMap<Integer, Set<Score>> scores = new TreeMap<>();
    private final Map<Integer, Integer> userHighScores = new ConcurrentHashMap<>();

    public void recordScore(int userId, int score) {
        userHighScores.compute(userId, (userIdKey, oldScore) -> {
            oldScore = oldScore == null ? 0 : oldScore;
            if (score > oldScore) {
                updateHighScores(userId, oldScore, score);
                return score;
            } else {
                return oldScore;
            }
        });
    }

    private void updateHighScores(int userId, int oldScore, int scoreValue) {
        synchronized (scores) {
            scores.computeIfPresent(oldScore, (scoreKey, scores) -> {
                scores.removeIf(score -> score.userId() == userId);
                return scores;
            });
            scores.compute(scoreValue, (k, v) -> {
                var score = new Score(userId, scoreValue);
                Set<Score> set = v == null ? new HashSet<>() : v;//not synchronized, but we won't be modifying it concurrently
                set.add(score);
                return set;
            });
        }
    }

    public HighScores getHighScores() {
        return getHighScores(DEFAULT_SCORES_LIMIT);
    }

    public HighScores getHighScores(int limit) {
        return new HighScores(
                scores.descendingMap().entrySet().stream().flatMap(entry -> entry.getValue().stream())
                        .limit(limit).collect(Collectors.toList()));
    }
}
