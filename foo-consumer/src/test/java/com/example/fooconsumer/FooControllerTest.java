package com.example.fooconsumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FooControllerTest {

    private static final int SNP_PROVIDER_PORT = 8093;

    private final FooController controller = new FooController(SNP_PROVIDER_PORT);

    @Rule
    public PactProviderRuleMk2 mockProvider =
            new PactProviderRuleMk2("snp-provider-gradle", "localhost", SNP_PROVIDER_PORT, this);


    @Pact(consumer="foo-consumer-gradle")
    public RequestResponsePact patients(PactDslWithProvider builder) {
        return builder
                .given("")
                .uponReceiving("Represents a successful scenario of getting patient demographics")
                .path("/api/patient/demographics/1")
                .method("GET")
                .headers("Content-Type", "application/json")
                .willRespondWith()
                .status(200)
                .body("{\"firstName\":\"John\",\"lastName\":\"Snow\",\"dob\":19930218,\"active\": true}")
                .headers(responseHeaders())

                .given("")
                .uponReceiving("Represents an unsuccessful scenario of getting patient demographics")
                .path("/api/patient/demographics/2")
                .method("GET")
                .headers("Content-Type", "application/json")
                .willRespondWith()
                .status(200)
                .body("{\"firstName\":\"Nick\",\"lastName\":\"Star\",\"dob\":19891006,\"active\": false}")
                .headers(responseHeaders())

                .given("")
                .uponReceiving("Represents a scenario of getting non existing patient")
                .path("/api/patient/demographics/33")
                .method("GET")
                .headers("Content-Type", "application/json")
                .willRespondWith()
                .status(404)

                .toPact();
    }

    @Test
    @PactVerification
    public void runTestBeer() {
        // ACTIVE
        assertEquals(this.controller.getActiveStatus(1).toString(), "ACTIVE");

        // NOT_ACTIVE
        assertEquals(this.controller.getActiveStatus(2).toString(), "NOT_ACTIVE");

        //NOT_FOUND
        assertEquals(this.controller.getActiveStatus(33).toString(), "UNKNOWN");
    }

    private Map<String, String> responseHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json;charset=UTF-8");
        return map;
    }
}
