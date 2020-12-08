package com.aws.codestar.projecttemplates.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class HWController {

    private static final String MESSAGE_FORMAT = "Hello %s! From updated Controller";

    @RequestMapping(method = {GET, POST}, produces = "application/json")
    public Map<String, String> helloWorldGet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return Collections.singletonMap("Output", String.format(MESSAGE_FORMAT, name));
    }
}
