package com.artarkatesoft.coronavirustracker.controllers.admin;

import com.artarkatesoft.coronavirustracker.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learn")
public class LearningController {


    @Autowired
    private Environment environment;

    @GetMapping("envdetails")
    public String getEnvironmentDetails() {
        return environment.toString();
    }


    @Autowired
    private CoronaDataService coronaDataService;

    @GetMapping("updateSummary")
    public String updateSummary() {
        coronaDataService.updateSummary();
        return "Ok";
    }


}
