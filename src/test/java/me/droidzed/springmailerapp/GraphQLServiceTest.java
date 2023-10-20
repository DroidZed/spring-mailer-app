package me.droidzed.springmailerapp;

import lombok.extern.slf4j.Slf4j;
import me.droidzed.springmailerapp.service.greeter.GreeterService;
import me.droidzed.springmailerapp.service.greeter.GreeterServiceImpl;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class GraphQLServiceTest {

    @Autowired
    private GraphQlTester tester;

   @Test
   @Order(1)
   @DisplayName("GraphQL Greeting")
   void shouldTheGreetingWorkAndNotThrowAnError() {
       String confirmationMsg = this.tester.document("{ hello(name: \"Aymen\") }")
               .execute()
               .path("hello")
               .entity(String.class)
               .get();

       log.info(confirmationMsg);

       Assertions.assertNotNull(confirmationMsg);
       Assertions.assertNotEquals("", confirmationMsg);
   }

    @Test
    @Order(2)
    @DisplayName("Sending Email With Mutation")
    void shouldSendingEmailWithMutationWork() {
        String mutation = "mutation ($emailInput: MailInput!) { sendMail(mail: $emailInput) { msg } }";

        MailInput testInput = new MailInput();

        testInput.setFrom("aymen.dhahri@esprit.tn");
        testInput.setName("Spring Mailer App");
        testInput.setSubject("Test mailer with GraphQL");
        testInput.setBody("This module is hard !");

        Response serviceResponse = this.tester.document(mutation)
                .variable("emailInput", testInput.toMap())
                .execute()
                .path("sendMail")
                .entity(Response.class)
                .get();

        Assertions.assertNotNull(serviceResponse);
    }
}
