package com.task1.greetperson.service;

import com.task1.greetperson.constants.SpecialCharacters;
import com.task1.greetperson.exception.EmptyRequestException;
import com.task1.greetperson.exception.GreetWordUnavailableException;
import com.task1.greetperson.exception.PersonNameUnavailableException;
import com.task1.greetperson.model.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetPersonService {

    private static final Logger logger = LoggerFactory.getLogger(GreetPersonService.class);

    private final RestTemplate restTemplate;
    private final Environment env;

    @Autowired
    public GreetPersonService(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.env = environment;
    }

    public String generateGreetPersonMessage(PersonInfo personInfo){

        logger.info("Generating greet message, getting greet word from greetWord service");

        String greetWord = getGreetWord();

        logger.info(String.format("Received greetWord %s", greetWord));

        logger.info("Getting personName from PersonName Service");

        String personName = getPersonName(personInfo);

        logger.info(String.format("Received personName %s", personName));

        StringBuilder sb = new StringBuilder(greetWord).append(SpecialCharacters.SPACE.value).append(personName);

        logger.info(String.format("generated greeting message %s",sb));

        return sb.toString();
    }

    private String getGreetWord() {
        try{
            return restTemplate.getForObject(getGreetWordUrl(), String.class);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new GreetWordUnavailableException();
        }
    }

    private String getPersonName(PersonInfo personInfo) {

        validateRequest(personInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PersonInfo> httpEntity = new HttpEntity<>(personInfo, headers);
        try {
            return restTemplate.postForObject(getPersonNameUrl(), httpEntity, String.class);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new PersonNameUnavailableException();
        }

    }

    private String getPersonNameUrl() {
        return env.getProperty("person.name.service.url");
    }

    private String getGreetWordUrl() {
        return env.getProperty("greet.word.service.url");
    }

    private void validateRequest(PersonInfo personInfo) {

        if(personInfo == null) throw new EmptyRequestException();

        if(StringUtils.isEmpty(personInfo.getFirstName()) && StringUtils.isEmpty(personInfo.getLastName())) throw new EmptyRequestException();

    }

}
