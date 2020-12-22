package com.artarkatesoft.coronavirustracker.controllers.admin;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getFileTest() {

        Parser parser = new Parser();
        File file = parser.getFile();
        System.out.println(file);
        StringBuilder builder = new StringBuilder();
    }

}
