package com.task1.greetperson.controller;

import com.task1.greetperson.constants.ServerStatus;
import com.task1.greetperson.model.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.task1.greetperson.service.GreetPersonService;

@RestController
@RequestMapping("/greet")
public class GreetPersonController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetPersonController.class);

    private final GreetPersonService greetPersonService;

    @Autowired
    public GreetPersonController(GreetPersonService greetPersonService) {
        this.greetPersonService = greetPersonService;
    }

    @GetMapping(value="/server-status", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getServerStatus(){
        LOG.info("Call for server status");
        return ServerStatus.UP.value;
    }

    @PostMapping(value = "/greet-person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String greetPerson(@RequestBody PersonInfo personInfo){
        LOG.info(String.format("Received call for greeting person %s", personInfo));
        return greetPersonService.generateGreetPersonMessage(personInfo);
    }
}
