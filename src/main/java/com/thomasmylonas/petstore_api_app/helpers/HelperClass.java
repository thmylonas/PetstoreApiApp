package com.thomasmylonas.petstore_api_app.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

public class HelperClass {

    public static final Random RANDOM = new Random();

    public static String stacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
