package me.droidzed.springmailerapp.service.greeter;

import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greeting() {
        return "This is used to test if GraphQL works or not.";
    }
}
