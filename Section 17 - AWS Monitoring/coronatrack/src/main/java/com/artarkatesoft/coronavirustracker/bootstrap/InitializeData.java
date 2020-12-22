package com.artarkatesoft.coronavirustracker.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class InitializeData implements CommandLineRunner {

    @Value("${AWS_XRAY_DAEMON_ADDRESS:127.0.0.1:2000}")
    private String daemonAddress;

    @Override
    public void run(String... args) throws Exception {
        log.info("Docker host address is (AWS_XRAY_DAEMON_ADDRESS) {}", daemonAddress);
    }
}
