package com.task1.greetword.controller;

import com.task1.greetword.constants.GreetWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class GreetWordController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetWordController.class);

    @GetMapping(value="/greet-word", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getServerStatus(){
        LOG.info(String.format("Received request for Greet word. Returning %s", GreetWords.HELLO.value));
        return new ResponseEntity<>(GreetWords.HELLO.value, HttpStatus.OK);
    }
}
