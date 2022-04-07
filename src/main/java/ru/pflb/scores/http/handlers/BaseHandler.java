package ru.pflb.scores.http.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public interface BaseHandler {

    default void sendSuccessResponse(HttpExchange exchange, String response) {
        try (exchange) {
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioe) {
            System.out.println("FATAL: Error trying to send response");
            ioe.printStackTrace();
        }
    }

    default void sendErrorResponse(HttpExchange exchange) {
        try (exchange) {
            exchange.sendResponseHeaders(404, -1);
        } catch (IOException ignored) {
            // no use trying to recover
        }
    }
}
