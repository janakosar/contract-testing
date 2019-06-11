package com.example.snpprovidergradle.service;

import com.example.snpprovidergradle.model.Demographics;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DemographicsService {

    private final List<Demographics> patients = Arrays.asList(
            new Demographics("John", "Snow", 19930218, true),
            new Demographics("Nick", "Star", 19891006, false),
            new Demographics("Emily", "White", 19840220, true),
            new Demographics("Alex", "Vectro", 19870613, false)
    );

    public Demographics getDemographics(int patientId) {

        if (patients.size() >= patientId) {
            return patients.get(patientId - 1);
        }else {
            return null;
        }
    }
}
