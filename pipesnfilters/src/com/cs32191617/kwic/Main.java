package com.cs32191617.kwic;


import com.cs32191617.kwic.indexbuilder.IndexBuilder;
import com.cs32191617.kwic.components.Input;
import com.cs32191617.kwic.components.Output;


public class Main {

    public static void main(String[] args) {
        IndexBuilder iBuilder = Input.readInput("input.txt");
        Output.saveOutput("output.txt",iBuilder);
    }
}
