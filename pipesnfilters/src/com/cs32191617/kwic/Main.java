package com.cs32191617.kwic;

import com.cs32191617.kwic.components.IgnoreListParser;
import com.cs32191617.kwic.indexbuilder.IndexBuilder;

public class Main {

    public static void main(String[] args) {
        String ignoreInput = "and, the, of";
        IndexBuilder iBuilder = new IndexBuilder(IgnoreListParser.generateIgnoreList(ignoreInput));
        iBuilder.generateAndAddIndexes("The lord of the Rings");
        iBuilder.generateAndAddIndexes("Harry potter and the deathly Hallows");
        String[] indexes = iBuilder.getIndexes();
        for(String index: indexes){
            System.out.println(index);
        }
    }
}
