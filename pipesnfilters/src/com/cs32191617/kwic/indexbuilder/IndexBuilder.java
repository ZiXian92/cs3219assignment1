package com.cs32191617.kwic.indexbuilder;

import com.cs32191617.kwic.components.Alphabetizer;
import com.cs32191617.kwic.components.CircularShifter;
import com.cs32191617.kwic.components.Filter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.TreeSet;

/**
 * Created by zixian on 8/18/16.
 */
public class IndexBuilder {
    private Alphabetizer alphabetizer;
    private CircularShifter shifter;
    private Filter indexFilter;
    private TreeSet<String> sortedIndexes, wordsToIgnore;

    /**
     * Creates a new IndexBuilder instance
     * @param wordsToIgnore The list of words to ignore in the inputs, case-insensitive
     */
    public IndexBuilder(String[] wordsToIgnore){
        this.alphabetizer = new Alphabetizer(wordsToIgnore);
        this.shifter = new CircularShifter();
        this.indexFilter = new Filter(wordsToIgnore);
        this.sortedIndexes = new TreeSet<String>();
        this.wordsToIgnore = new TreeSet<String>(Arrays.stream(wordsToIgnore).map(word -> word.toLowerCase()).sorted().collect(Collectors.toList()));
    }

    /**
     * Generates indexes based on keywords in the given input and
     * adds them to an index list.
     * @param input The input for which to generate indexes for
     * @return true if the operaiion is successful and false otherwise
     */
    public boolean generateAndAddIndexes(String input){

        // Generates a stream and passes it through a pipeline of filters
        Arrays.stream(new String[]{input})
        .map(str -> this.alphabetizer.alphabetize(str))
        .flatMap(str -> this.shifter.generateIndexes(str).stream())
        .filter(index -> this.indexFilter.isKeywordIndex(index))
        .forEach(index -> this.sortedIndexes.add(index));

        // This implementation works according to defined requirements specifications
        // but is not modularized nor is each step of the pipe easily testable.
        // For implementation reference when implementing the components.
//        ArrayList<String> arr = new ArrayList<String>();
//        arr.add(input);
//        arr.stream().flatMap(originalStr -> {
//            // Alphabetizing
//            ArrayList<String> words = new ArrayList<String>(Arrays.stream(originalStr.split("\\s+")).map(word -> {
//                if(this.wordsToIgnore.contains(word.toLowerCase())) return word;
//                char[] letters = word.toCharArray();
//                letters[0] = Character.toUpperCase(letters[0]);
//                return new String(letters);
//            }).collect(Collectors.toList()));
//
//            // Circular Shifting
//            int numWords = words.size();
//            ArrayList<String> outputArr = new ArrayList<String>();
//            for(int i=0; i<numWords; i++){
//                outputArr.add(words.stream().collect(Collectors.joining(" ")).toString());
//                words.add(words.get(0));
//                words.remove(0);
//            }
//            return outputArr.stream();
//        }).filter(index -> !this.wordsToIgnore.contains(index.split("\\s+", 2)[0].toLowerCase()))   // Filtering
//        .forEach(index -> this.sortedIndexes.add(index));   // Accumulating
        return true;
    }

    /**
     * @return Sorted list of indexes
     */
    public String[] getIndexes(){
        return this.sortedIndexes.toArray(new String[this.sortedIndexes.size()]);
    }
}
