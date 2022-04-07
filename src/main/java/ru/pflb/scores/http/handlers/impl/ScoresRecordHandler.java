package ru.pflb.scores.http.handlers.impl;

import com.sun.net.httpserver.HttpExchange;
import ru.pflb.scores.http.handlers.BaseHandler;
import ru.pflb.scores.http.handlers.PathAwareHandler;
import ru.pflb.scores.service.ScoreService;

import java.io.IOException;

public class ScoresRecordHandler implements BaseHandler, PathAwareHandler {

    private static final String EMPTY_STRING = "";

    private final ScoreService scoreService;

    public ScoresRecordHandler(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public void handle(HttpExchange exchange, String... pathChunks) throws IOException{
        if (!"POST".equals(exchange.getRequestMethod())) {
            sendErrorResponse(exchange);
        }
        var levelId = Integer.parseInt(pathChunks[1]);
        var sessionKey = exchange.getRequestURI().getQuery().split("=")[1];
        var score = Integer.parseInt(new String(exchange.getRequestBody().readAllBytes()));
        try {
            scoreService.record(sessionKey, score, levelId);
        } catch (IllegalStateException e) {
            sendErrorResponse(exchange);
        }
        this.sendSuccessResponse(exchange, EMPTY_STRING);
    }
}
