package ru.pflb.scores.http.handlers.impl;

import com.sun.net.httpserver.HttpExchange;
import ru.pflb.scores.http.handlers.BaseHandler;
import ru.pflb.scores.http.handlers.PathAwareHandler;
import ru.pflb.scores.service.LoginService;

public class LoginHandler implements BaseHandler, PathAwareHandler {

    private final LoginService loginService;

    public LoginHandler(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void handle(HttpExchange exchange, String... pathChunks) {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendErrorResponse(exchange);
        }
        var userId = Integer.parseInt(pathChunks[1]);
        var sessionKey = this.loginService.login(userId);
        this.sendSuccessResponse(exchange, sessionKey);
    }
}
