package com.artarkatesoft.coronavirustracker.controllers.admin;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.file.Files;

public class Parser {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String readContent() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
//        inputStream.read()
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        inputStreamReader.
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
//        Channel channel =

        Throwable this_is_throwable = new Throwable("This is Throwable");

        return null;

    }
}
