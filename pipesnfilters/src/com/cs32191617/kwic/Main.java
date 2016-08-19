package com.cs32191617.kwic;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.cs32191617.kwic.indexbuilder.IndexBuilder;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String ignoreInput = "the, of";
        String[] wordsToIgnore = Arrays.stream(ignoreInput.split(",")).map(word -> word.trim()).collect(Collectors.toList()).toArray(new String[0]);
        System.out.println("Words to ignore:");
        for(String word: wordsToIgnore) System.out.println(word);
        IndexBuilder iBuilder = new IndexBuilder(wordsToIgnore);
        iBuilder.generateAndAddIndexes("The lord of the Rings");
        String[] indexes = iBuilder.getIndexes();
        for(String index: indexes){
            System.out.println(index);
        }
    }
}
