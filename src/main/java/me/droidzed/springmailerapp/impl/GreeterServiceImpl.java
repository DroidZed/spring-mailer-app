package me.droidzed.springmailerapp.impl;

import me.droidzed.springmailerapp.service.GreeterService;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greeting() {
        return "This is used to test if GraphQL works or not.";
    }
}
