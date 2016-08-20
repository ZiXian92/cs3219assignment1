package com.cs32191617.kwic;

import com.cs32191617.kwic.keywordbuilder.KeywordBuilder;
import com.cs32191617.kwic.pubsub.ActionCreator;
import com.cs32191617.kwic.pubsub.Dispatcher;
import com.cs32191617.kwic.stores.KeywordStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = Dispatcher.getInstance();
        KeywordBuilder keywordBuilder;
        KeywordStore keywordStore = new KeywordStore();
        String input;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            // Read in words to ignore
            System.out.println("Enter list of words to ignore(comma-separated):");
            input = reader.readLine();
            if(input!=null){
                // Process the words to ignore by creating the keyword builder
                // What is the best way to do so using implicit invocation?
                // Or is explicit invocation the only way to set things up despite the architecture being implicit invocation?
                keywordBuilder = new KeywordBuilder(input);

                System.out.println("Enter phrases, 1 on each line. Terminate by entering Ctrl+D");

                // Keep reading inputs and generating indexes
                input = reader.readLine();
                while(input!=null){
                    dispatcher.dispatch(ActionCreator.handleKwicInput(input));
                    input = reader.readLine();
                }

                // Print output or dispatch an action to do so
                // To be implemented
            }
        } catch(IOException e){
            System.out.println("IO error occurred. Terminating application.");
        }
    }
}
