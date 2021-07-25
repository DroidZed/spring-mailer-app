package me.droidzed.springmailerapp.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import me.droidzed.springmailerapp.service.GreeterService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GreeterResolver implements GraphQLQueryResolver {

    private final GreeterService greeterService;

    public String hello() {
        return this.greeterService.greeting();
    }
}
