package ru.pflb.scores.http.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface PathAwareHandler {

    void handle(HttpExchange exchange, String... pathChunks) throws IOException;
}
