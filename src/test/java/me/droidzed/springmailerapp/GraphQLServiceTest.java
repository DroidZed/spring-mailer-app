package me.droidzed.springmailerapp;

import lombok.extern.slf4j.Slf4j;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class GraphQLServiceTest {

    @Autowired
    private GraphQlTester tester;

    @Test
    void shouldTheGreetingWorkAndNotThrowAnError() {

        String query = "{ hello }";

        String confirmationMsg = tester.document(query)
                .execute()
                .path("hello")
                .entity(String.class)
                .get();

        log.info(confirmationMsg);

        Assertions.assertNotNull(confirmationMsg);
        Assertions.assertNotEquals("", confirmationMsg);
    }

    @Test
    void shouldSendingEmailWithMutationWork() {
        String mutation = "mutation ($emailInput: MailInput!) { sendMail(mail: $emailInput) { msg } }";

        MailInput testInput = new MailInput();

        testInput.setFrom("aymen.dhahri@esprit.tn");
        testInput.setName("Spring Mailer App");
        testInput.setSubject("Test mailer with GraphQL");
        testInput.setBody("This module is hard !");

        Response serviceResponse = tester.document(mutation)
                .variable("emailInput", testInput.toMap())
                .execute()
                .path("sendMail")
                .entity(Response.class)
                .get();

        Assertions.assertNotNull(serviceResponse);
    }
}
