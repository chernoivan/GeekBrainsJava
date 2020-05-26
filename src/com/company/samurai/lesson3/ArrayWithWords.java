package com.company.samurai.lesson3;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArrayWithWords {
    private static String[] wordsArray = {
            "first", "first", "first",
            "second", "second", "second", "second",
            "third",
            "fourth",
            "fifth", "fifth"
    };

    public static void main(String[] args) {
        wordsCounter(wordsArray);
    }

    public static void wordsCounter(String[] arr){
        Map<String, Integer> entryArr = new LinkedHashMap<>();
        for (String word : arr) {
            entryArr.put(word, entryArr.getOrDefault(word, 0) + 1);
        }
        System.out.println(entryArr);
    }
}
