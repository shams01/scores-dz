package ru.pflb.scores;

import ru.pflb.scores.http.ServerConfiguration;
import ru.pflb.scores.service.impl.InMemoryScoreService;
import ru.pflb.scores.service.impl.NoAuthLoginService;
import ru.pflb.scores.util.SessionEvictingHolder;
import ru.pflb.scores.http.handlers.RoutingHandler;
import ru.pflb.scores.http.handlers.impl.HighScoresHandler;
import ru.pflb.scores.http.handlers.impl.LoginHandler;
import ru.pflb.scores.http.handlers.impl.ScoresRecordHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScoresApp {

    public static void main(String[] args) {
        var sc = new ServerConfiguration(8080, routingHandler(), threadPoolExecutor());
        sc.start();
    }

    private static Executor threadPoolExecutor() {
        return new ThreadPoolExecutor(10, 30, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1200));
    }

    private static RoutingHandler routingHandler() {
        var sessionHolder = new SessionEvictingHolder();
        var loginService = new NoAuthLoginService(sessionHolder);
        var loginHandler = new LoginHandler(loginService);
        var scoreService = new InMemoryScoreService(loginService);
        var scoresRecordHandler = new ScoresRecordHandler(scoreService);
        var highScoresHandler = new HighScoresHandler(scoreService);
        return new RoutingHandler(loginHandler, scoresRecordHandler, highScoresHandler);
    }
}
