package com.cs32191617.kwic;

import com.cs32191617.kwic.components.IgnoreListParser;
import com.cs32191617.kwic.indexbuilder.IndexBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        String input;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            // Read in words to ignore
            System.out.println("Enter list of words to ignore(comma-separated):");
            input = reader.readLine();
            if(input!=null){
                IndexBuilder iBuilder = new IndexBuilder(IgnoreListParser.generateIgnoreList(input));
                System.out.println("Enter phrases, 1 on each line. Terminate by entering Ctrl+D");

                // Keep reading inputs and generating indexes
                input = reader.readLine();
                while(input!=null){
                    iBuilder.generateAndAddIndexes(input);
                    input = reader.readLine();
                }

                // Print output
                System.out.println("Keywords:");
                for(String index: iBuilder.getIndexes()){
                    System.out.println(index);
                }
            }
        } catch(IOException e){
            System.out.println("IO error occurred. Terminating application.");
        }
    }
}
