package ru.pflb.scores.http.handlers.impl;

import com.sun.net.httpserver.HttpExchange;
import ru.pflb.scores.http.handlers.BaseHandler;
import ru.pflb.scores.http.handlers.PathAwareHandler;
import ru.pflb.scores.service.ScoreService;

import java.io.IOException;

public class HighScoresHandler implements BaseHandler, PathAwareHandler {

    private final ScoreService scoreService;

    public HighScoresHandler(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public void handle(HttpExchange exchange, String... pathChunks) throws IOException{
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendErrorResponse(exchange);
        }
        var levelId = Integer.parseInt(pathChunks[1]);

        this.sendSuccessResponse(exchange, scoreService.highScores(levelId));
    }
}
