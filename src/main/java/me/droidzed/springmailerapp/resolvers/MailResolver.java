package me.droidzed.springmailerapp.resolvers;

import lombok.AllArgsConstructor;
import me.droidzed.springmailerapp.service.mailer.MailerService;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;


@Controller
@AllArgsConstructor
public class MailResolver {

    private final MailerService mailerService;

    @MutationMapping
    public Response sendMail(@Argument(name = "mail")  MailInput input) throws MessagingException {
        return this.mailerService.sendMailToMaster(input);
    }
}
