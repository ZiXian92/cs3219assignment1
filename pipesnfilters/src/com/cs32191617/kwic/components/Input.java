package com.cs32191617.kwic.components;

import com.cs32191617.kwic.indexbuilder.IndexBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Joan on 25/8/2016.
 */
public class Input {

    public static IndexBuilder readInput(String src){
        IndexBuilder iBuilder = null;
        try{

            FileInputStream fis = new FileInputStream(src);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            int numBytes;

            String line = br.readLine();
            while(line != null){
                iBuilder = new IndexBuilder(IgnoreListParser.generateIgnoreList(line));
                // Keep reading inputs and generating indexes
                line = br.readLine();
                while(line != null){
                    iBuilder.generateAndAddIndexes(line);
                    line = br.readLine();
                }
            }

            br.close();
            fis.close();
            System.out.println(src+" successfully read.");
        } catch(IOException e){
            System.out.println("IO error occurred. Terminating application.");
        }
        return iBuilder;
    }

}
