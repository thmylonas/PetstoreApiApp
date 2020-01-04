package com.thomasmylonas.petstore_api_web_app.helpers;

import java.util.ArrayList;
import java.util.List;

public class UsefulUtils {

    public static boolean isInteger(String id) {
        if (id == null) {
            return false;
        }
        try {
            Integer.parseInt(id);
//            Double.parseDouble(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static <T> List<T> arrayToList(T[] array) {

        List<T> list = new ArrayList<>();
        for (T e : array) {
            list.add(e);
        }
        return list;
    }
}
