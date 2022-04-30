package ru.pflb.scores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoresApp {

    public static void main(String[] args) {
        SpringApplication.run(ScoresApp.class, args);
    }

//
//    public static void main(String[] args) {
//        var sc = new ServerConfiguration(8080, routingHandler(), threadPoolExecutor());
//        sc.start();
//    }
//
//    private static Executor threadPoolExecutor() {
//        return new ThreadPoolExecutor(10, 30, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1200));
//    }
//
//    private static RoutingHandler routingHandler() {
//        var sessionHolder = new SessionEvictingHolder();
//        var loginService = new NoAuthLoginService(sessionHolder);
//        var loginHandler = new LoginHandler(loginService);
//        var scoreService = new InMemoryScoreService(loginService);
//        var scoresRecordHandler = new ScoresRecordHandler(scoreService);
//        var highScoresHandler = new HighScoresHandler(scoreService);
//        return new RoutingHandler(loginHandler, scoresRecordHandler, highScoresHandler);
//    }
}
