package me.droidzed.springmailerapp;

import lombok.extern.slf4j.Slf4j;
import me.droidzed.springmailerapp.service.mailer.MailerService;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MailingServiceTest {

    @Autowired
    private MailerService mailerService;

    @Test
    void shouldTheMailingWork() {

        MailInput testInput = new MailInput();

        testInput.setFrom("aymen.dhahri@esprit.tn");
        testInput.setName("Spring Mailer App");
        testInput.setSubject("Test mailer");
        testInput.setBody("Hope this works !");

        try {
            final Response resp = mailerService.sendMailToMaster(testInput);
            Assertions.assertNotNull(resp);
            Assertions.assertNotEquals("", resp.getMsg());

        } catch (MessagingException e) {
            log.error(e.getMessage());
            Assertions.fail();
        }

    }
}
