package ru.pflb.scores.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoresController {


    @GetMapping(path = "/hiscores/{levelId}")
    //TODO implement

    @PostMapping(path = "/score/{levelId}/{userId}")
    //TODO implement; pass sessionKey as Query Param (/.../../..?session={sessionKey})
}
