package com.example.fooconsumer;

import com.example.fooconsumer.model.ActiveStatus;
import com.example.fooconsumer.model.Demographics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class FooController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final int port;

    FooController(@Value("${rest.provider.snp.port}") int port) {
        this.port = port;
    }

    @GetMapping(path = "/api/patient/{id}/active",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActiveStatus getActiveStatus(@PathVariable(name = "id") long pateintId) {

        Demographics demographics = requestDemographics(pateintId);

        if (demographics != null) {
            return demographics.isActive() ? ActiveStatus.ACTIVE : ActiveStatus.NOT_ACTIVE;
        } else {
            return ActiveStatus.UNKNOWN;
        }
    }

    private Demographics requestDemographics(long patientId) {

        try {
            return this.restTemplate.exchange(
                    RequestEntity
                            .get(URI.create("http://localhost:" + port + "/api/patient/demographics/" + patientId))
                            .header("Content-Type", "application/json")
                            .build(),
                    Demographics.class).getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }
    }


}
