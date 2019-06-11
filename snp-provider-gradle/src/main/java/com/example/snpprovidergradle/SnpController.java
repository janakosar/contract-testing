package com.example.snpprovidergradle;

import com.example.snpprovidergradle.model.Demographics;
import com.example.snpprovidergradle.service.DemographicsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnpController {

    private final DemographicsService demographicsService;

    public SnpController(DemographicsService demographicsService) {
        this.demographicsService = demographicsService;
    }

    @GetMapping(value = "/api/patient/demographics/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> getDemographics(@PathVariable("id") int patientId) {
        Demographics demographics =  demographicsService.getDemographics(patientId);

        if (demographics != null) {
            return ResponseEntity.ok(demographics);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
