package com.example.snpprovidergradle;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Profile("test")
public class StateChangeController {

    @RequestMapping(value = "/pactStateChange", method = RequestMethod.POST)
    public void providerState(@RequestBody Map body) {

    }
}
