package me.droidzed.springmailerapp.service.greeter;

import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greeting(String name) {
        return String.format("Good day/evening %s!", name);
    }
}
