package com.cs32191617.kwic.keywordbuilder;

import com.cs32191617.kwic.pubsub.Action;
import com.cs32191617.kwic.pubsub.ActionCreator;
import com.cs32191617.kwic.pubsub.Dispatcher;
import com.cs32191617.kwic.pubsub.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zixian on 8/20/16.
 */
public class KeywordBuilder implements Subscriber {
    private Alphabetizer alphabetizer;
    private CircularShifter shifter;
    private Filter filter;

    public KeywordBuilder(String ignoreListInput){
        List<String> ignoreList = IgnoreListParser.generateIgnoreList(ignoreListInput);
        this.alphabetizer = new Alphabetizer(ignoreList);
        this.shifter = new CircularShifter();
        this.filter = new Filter(ignoreList);
    }

    @Override
    public void handleAction(Action action){
        String input = (String)action.getData();

        // Reusing pipes and filters implementation for this keyword builder component
        // Is there a way to have its own implicit invocation system?
        List<String> keywords = Arrays.stream(new String[]{input})
        .map(phrase -> this.alphabetizer.alphabetize(phrase))
        .flatMap(phrase -> this.shifter.generateIndexes(phrase).stream())
        .filter(keyword -> this.filter.isKeywordIndex(keyword))
        .collect(Collectors.toList());

        Dispatcher.getInstance().dispatch(ActionCreator.handleKwicAddKeywords(keywords));
    }
}
