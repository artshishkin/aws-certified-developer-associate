package com.artarkatesoft.coronavirustracker.controllers;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@XRayEnabled
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/v2")
    public String homeV2() {
        return "index";
    }
}
