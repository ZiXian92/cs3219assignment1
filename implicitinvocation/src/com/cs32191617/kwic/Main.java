package com.cs32191617.kwic;

import com.cs32191617.kwic.keywordbuilder.KeywordBuilder;
import com.cs32191617.kwic.misc.InputReader;
import com.cs32191617.kwic.misc.OutputFormatter;
import com.cs32191617.kwic.pubsub.ActionCreator;
import com.cs32191617.kwic.pubsub.Dispatcher;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = Dispatcher.getInstance();
        KeywordBuilder keywordBuilder;
        String input;

        try(InputReader reader = new InputReader(); OutputFormatter writer = new OutputFormatter()){

            // Read in words to ignore
            input = reader.readInputLine();
            if(input!=null){
                // Process the words to ignore by creating the keyword builder
                keywordBuilder = new KeywordBuilder(input);

                System.out.println("Enter phrases, 1 on each line. Terminate by entering Ctrl+D");

                // Keep reading inputs and generating indexes
                input = reader.readInputLine();
                while(input!=null){
                    dispatcher.dispatch(ActionCreator.handleKwicInput(input));
                    input = reader.readInputLine();
                }

                // Print output or dispatch an action to do so
                List<String> keywords = keywordBuilder.getKeywords();
                writer.writeStringList(keywords);
            }
        } catch(IOException e){
            System.out.println("IO error occurred. Terminating application.");
        }
    }
}
