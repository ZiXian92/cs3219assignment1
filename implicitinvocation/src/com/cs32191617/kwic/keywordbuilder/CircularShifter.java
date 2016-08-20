package com.cs32191617.kwic.keywordbuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by zixian on 8/18/16.
 */
public class CircularShifter {

    /**
     * For each item in input, generate all possible indexes using circular rotation strategy.
     * @param str The input string
     * @return A list of all generated indexes for the given input
     */
    public List<String> generateIndexes(String str){
        String[] wordList = str.split("\\s+");
        ArrayList<String> indexList= new ArrayList<String>();
        String currentShift;
        for (int i=0; i<wordList.length; i++) {
            currentShift = "";
            for(int j=i; j<(wordList.length + i ); j++){
                currentShift += wordList[j % wordList.length];
                if(j < (wordList.length + i - 1))
                    currentShift += " ";
            }
            indexList.add(currentShift);
        }
        return indexList;
    }
}
