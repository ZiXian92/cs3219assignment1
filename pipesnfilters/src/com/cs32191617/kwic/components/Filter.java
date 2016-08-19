package com.cs32191617.kwic.components;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zixian on 8/19/16.
 *
 * Defines the component that filters indexes based on a list of non-keywords.
 */
public class Filter {
    private TreeSet<String> wordsToIgnore;

    public Filter(String[] ignoreList){
        this.wordsToIgnore = new TreeSet<String>(Arrays.stream(ignoreList).sorted().collect(Collectors.toList()));
    }

    /**
     * Checks if the given string is considered a keyword index.
     * Comparisons are case-insensitive.
     * @param str The string to be checked
     * @return True if str does not start with any of the words supplied in the constructor, false otherwise
     */
    public boolean isKeywordIndex(String str){
        // To be implemented
        return true;
    }
}
