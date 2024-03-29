package me.droidzed.springmailerapp.resolvers;

import lombok.AllArgsConstructor;
import me.droidzed.springmailerapp.service.greeter.GreeterService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GreeterResolver {

    private final GreeterService greeterService;

    @QueryMapping
    public String hello(@Argument(name = "name") String name) {
        return this.greeterService.greeting(name);
    }
}
