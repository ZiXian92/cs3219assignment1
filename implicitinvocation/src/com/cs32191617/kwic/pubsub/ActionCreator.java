package com.cs32191617.kwic.pubsub;

import java.util.List;

/**
 * Created by zixian on 8/20/16.
 *
 * Factory class to abstract away the creation of action objects.
 */
public class ActionCreator {

    public static Action<List<String>> handleKwicAddKeywords(List<String> keywords){
        return new Action<List<String>>(ActionTypes.KWIC_ADD_KEYWORDS, keywords);
    }

    public static Action<String> handleKwicIgnoreListInput(String input){
        return new Action<String>(ActionTypes.KWIC_IGNORE_LIST_INPUT, input);
    }

    public static Action<String> handleKwicInput(String input){
        return new Action<String>(ActionTypes.KWIC_TEXT_INPUT, input);
    }
}
