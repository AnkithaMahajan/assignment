package com.task1.greetperson;

import com.task1.greetperson.controller.GreetPersonController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GreetpersonApplicationTests{

    @Autowired
    private GreetPersonController greetPersonController;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertNotNull(greetPersonController);
    }
}
