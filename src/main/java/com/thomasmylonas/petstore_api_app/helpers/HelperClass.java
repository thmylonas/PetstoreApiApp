package com.thomasmylonas.petstore_api_app.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HelperClass {

    public static final Random RANDOM = new Random();

    public static List<Long> randomLongNumbers(int amount) {
        return Stream.generate(() -> HelperClass.RANDOM.nextLong(100)).limit(amount).distinct().toList();
    }

    public static String stacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
