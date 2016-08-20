package com.cs32191617.kwic.stores;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by zixian on 8/20/16.
 */
public class KeywordStore {
    private TreeSet<String> keywords;

    public KeywordStore(){
        this.keywords = new TreeSet<String>();
    }

    /**
     * Adds the given list of keywrods to the store.
     * @param keywords The list of keywords to add
     */
    public void addKeywords(List<String> keywords){
        keywords.forEach(keyword -> this.keywords.add(keyword));
    }

    /**
     * @return Lexicographically sorted list of keywords
     */
    public List<String> getKeywords(){
        return this.keywords.stream().collect(Collectors.toList());
    }
}
