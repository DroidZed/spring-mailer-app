package me.droidzed.springmailerapp.impl;

import me.droidzed.springmailerapp.service.GreeterService;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greeting() {
        return "Hello there, this API does not have anything for GET requests, try going back to the home page and check what you can do !";
    }
}
