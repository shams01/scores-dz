package ru.pflb.scores.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class ServerConfiguration {

    private static final int BACKLOG_DEFAULT = 0;
    private final InetSocketAddress inetSocketAddress;
    private final HttpHandler routingHandler;
    private final Executor executor;

    public ServerConfiguration(int port, HttpHandler routingHandler, Executor executor) {
        this.inetSocketAddress = new InetSocketAddress(port);
        this.routingHandler = routingHandler;
        this.executor = executor;
    }

    public void start() {
        try {
            var server = HttpServer.create(inetSocketAddress, BACKLOG_DEFAULT);
            configureServer(server);
            server.start();
        } catch (IOException e) {
            System.out.println("FATAL: Failed to initialize server!");
            e.printStackTrace();
        }
    }

    public void configureServer(HttpServer server) {
        server.createContext("/", routingHandler);
        server.setExecutor(executor);
    }
}
