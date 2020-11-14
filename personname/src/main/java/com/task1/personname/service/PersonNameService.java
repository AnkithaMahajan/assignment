package com.task1.personname.service;

import com.task1.personname.constants.SpecialCharacters;
import com.task1.personname.model.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonNameService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonNameService.class);

    public String generatePersonName(PersonInfo personInfo) {

        LOG.info(String.format("Generating person name from personInfo.Firstname %s and Lastname %s", personInfo.getFirstName(), personInfo.getLastName()));

        StringBuilder sb = new StringBuilder();
        sb.append(personInfo.getFirstName() == null ? "":personInfo.getFirstName())
                .append(personInfo.getLastName() == null ? "": new StringBuilder(SpecialCharacters.SPACE.value).append(personInfo.getLastName()));

        LOG.info(String.format("Returning generated name %s", sb));

        return sb.toString();

    }

}
