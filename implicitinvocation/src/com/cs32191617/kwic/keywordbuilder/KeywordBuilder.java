package com.cs32191617.kwic.keywordbuilder;

import com.cs32191617.kwic.pubsub.*;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by zixian on 8/20/16.
 *
 * This component uses pipes and filters architecture as it is the most natural
 * modelling for the conversion process.
 */
public class KeywordBuilder implements Subscriber {
    private Capitalizer capitalizer;
    private CircularShifter shifter;
    private Filter filter;
    private TreeSet<String> store;

    /**
     * @param ignoreListInput Comma-separated list of words to ignore
     */
    public KeywordBuilder(String ignoreListInput){
        List<String> ignoreList = IgnoreListParser.generateIgnoreList(ignoreListInput);
        this.capitalizer = new Capitalizer(ignoreList);
        this.shifter = new CircularShifter();
        this.filter = new Filter(ignoreList);
        this.store = new TreeSet<String>();
    }

    @Override
    public void handleAction(Action action){
        switch(action.getActionType()){
            case KWIC_TEXT_INPUT:
                assert (action.getData() instanceof String);
                this.generateAndAddKeywords((String)action.getData());
            default: return;
        }
    }

    public void generateAndAddKeywords(String input){
        Arrays.stream(new String[]{input})
                .map(phrase -> this.capitalizer.alphabetize(phrase))
                .flatMap(phrase -> this.shifter.generateIndexes(phrase).stream())
                .filter(keyword -> this.filter.isKeywordIndex(keyword))
                .forEach(keyword -> this.store.add(keyword));
    }

    /**
     * Gets sorted list of keywords
     * @return Sorted list of keywords
     */
    public List<String> getKeywords(){
        return this.store.stream().collect(Collectors.toList());
    }
}
