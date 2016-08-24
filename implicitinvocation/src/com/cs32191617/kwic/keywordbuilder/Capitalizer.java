package com.cs32191617.kwic.keywordbuilder;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by zixian on 8/19/16.
 */
public class Capitalizer {
    private TreeSet<String> wordsToIgnore;

    public Capitalizer(List<String> ignoreList){
        this.wordsToIgnore = ignoreList.stream().collect(Collectors.toCollection(() -> new TreeSet<String>()));
    }

    /**
     * Capitalize the 1st character of each keyword.
     * @param str The string to be alphabetized
     * @return The alphabetized string
     */
    public String alphabetize(String str){
        return Arrays.stream(str.split("\\s+"))
                .map(word -> {
                    if(word.isEmpty() || this.wordsToIgnore.contains(word.toLowerCase())) return word;
                    char[] letters = word.toCharArray();
                    letters[0] = Character.toUpperCase(letters[0]);
                    return new String(letters);
                }).collect(Collectors.joining(" "));
    }
}
