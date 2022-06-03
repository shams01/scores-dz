package ru.pflb.scores.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


public interface ScoreService {

    void record(String sessionKey, int score, int levelId) throws IllegalStateException;

    String highScores(int levelId);
}
