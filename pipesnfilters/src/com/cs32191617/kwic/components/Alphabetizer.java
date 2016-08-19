package com.cs32191617.kwic.components;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zixian on 8/19/16.
 */
public class Alphabetizer {
    private TreeSet<String> wordsToIgnore;

    public Alphabetizer(String[] ignoreList){
        this.wordsToIgnore = new TreeSet<String>(Arrays.stream(ignoreList).sorted().collect(Collectors.toList()));
    }

    /**
     * Capitalize the 1st character of each keyword.
     * @param str The string to be alphabetized
     * @return The alphabetized string
     */
    public String alphabetize(String str){
        return str;
    }
}
