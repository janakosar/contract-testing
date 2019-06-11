package com.example.snpprovidergradle;

import com.example.snpprovidergradle.service.DemographicsService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class SnpContractTest {

    SnpController snpController = new SnpController( new DemographicsService());

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(snpController);
    }
}
