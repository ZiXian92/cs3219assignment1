package com.cs32191617.kwic.components;

/**
 * Created by zixian on 8/19/16.
 *
 * Defines a parser that parses comma-separated list of words to ignore, keeping them in lowercase.
 */

import java.util.Arrays;
import java.util.stream.Collectors;

public class IgnoreListParser {
    public static String[] generateIgnoreList(String input){
        return Arrays.stream(input.split(",")).map(word -> word.trim().toLowerCase()).collect(Collectors.toList()).toArray(new String[0]);
    }
}
