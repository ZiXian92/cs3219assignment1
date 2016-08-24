package com.cs32191617.kwic.keywordbuilder;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by zixian on 8/19/16.
 *
 * Defines the component that filters indexes based on a list of non-keywords.
 */
public class Filter {
    private TreeSet<String> wordsToIgnore;

    public Filter(List<String> ignoreList){
        this.wordsToIgnore = ignoreList.stream().collect(Collectors.toCollection(() -> new TreeSet<String>()));
    }

    /**
     * Checks if the given string is considered a keyword index.
     * Comparisons are case-insensitive.
     * @param str The string to be checked
     * @return True if str does not start with any of the words supplied in the constructor, false otherwise
     */
    public boolean isKeywordIndex(String str){
        String firstWord = str.split("\\s+", 2)[0].toLowerCase();
        return !this.wordsToIgnore.contains(firstWord);
    }
}
