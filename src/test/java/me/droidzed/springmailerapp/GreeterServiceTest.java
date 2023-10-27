package me.droidzed.springmailerapp;

import lombok.extern.slf4j.Slf4j;
import me.droidzed.springmailerapp.service.greeter.GreeterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.String.format;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class GreeterServiceTest {

    @Autowired
    private GreeterService greeterService;

    private static final String name = "Aymen";

    @Test
    @DisplayName("Greeter Test")
    public void testGreeting() {
        String result = greeterService.greeting(name);

        Assertions.assertEquals(format("Good day/evening %s!", name), result);
    }
}
