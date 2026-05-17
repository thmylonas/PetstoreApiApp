package com.thomasmylonas.petstore_api_app.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HelperClass {

    public static final Random RANDOM = new Random();

    public static final List<Long> RANDOM_ORDER_IDS = Stream.generate(() -> HelperClass.RANDOM.nextLong(100)).limit(10).distinct().toList();

    public static String stacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
