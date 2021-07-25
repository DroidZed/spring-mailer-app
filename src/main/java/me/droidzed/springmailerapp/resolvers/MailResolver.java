package me.droidzed.springmailerapp.resolvers;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import me.droidzed.springmailerapp.service.MailerService;
import me.droidzed.springmailerapp.types.MailInput;
import me.droidzed.springmailerapp.types.Response;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;

@Component
@AllArgsConstructor
public class MailResolver implements GraphQLMutationResolver {

    private final MailerService mailerService;

    public Response sendMail(MailInput input) throws MessagingException {
        return this.mailerService.sendMailToMaster(input);
    }
}
