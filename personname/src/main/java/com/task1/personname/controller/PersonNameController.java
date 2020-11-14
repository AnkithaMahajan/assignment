package com.task1.personname.controller;

import com.task1.personname.model.PersonInfo;
import com.task1.personname.service.PersonNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonNameController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonNameController.class);

    private PersonNameService personNameService;

    @Autowired
    public PersonNameController(PersonNameService personNameService) {
        this.personNameService = personNameService;
    }

    @PostMapping(value = "/person-name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String personName(@RequestBody PersonInfo personInfo){
        LOG.info(String.format("Received person name request %s", personInfo));
        return personNameService.generatePersonName(personInfo);
    }

}
