package ru.pflb.scores.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.pflb.scores.http.handlers.impl.HighScoresHandler;
import ru.pflb.scores.http.handlers.impl.LoginHandler;
import ru.pflb.scores.http.handlers.impl.ScoresRecordHandler;

import java.io.IOException;

public class RoutingHandler implements HttpHandler, BaseHandler {

    private final LoginHandler loginHandler;
    private final ScoresRecordHandler scoresRecordHandler;
    private final HighScoresHandler highScoresHandler;

    public RoutingHandler(LoginHandler loginHandler, ScoresRecordHandler scoresRecordHandler, HighScoresHandler highScoresHandler) {
        this.loginHandler = loginHandler;
        this.scoresRecordHandler = scoresRecordHandler;
        this.highScoresHandler = highScoresHandler;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var pathChunks = getPathParams(exchange);

        switch (pathChunks[2].toLowerCase()) {
            case "login" -> loginHandler.handle(exchange, pathChunks);
            case "score" -> scoresRecordHandler.handle(exchange, pathChunks);
            case "highscorelist" -> highScoresHandler.handle(exchange, pathChunks);
            default -> sendErrorResponse(exchange);
        }

    }

    private String[] getPathParams(HttpExchange exchange) {
        return exchange.getRequestURI().getPath().split("/");
    }
}