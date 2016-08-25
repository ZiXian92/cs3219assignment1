package com.cs32191617.kwic.components;

import com.cs32191617.kwic.indexbuilder.IndexBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Joan on 25/8/2016.
 */
public class Output {

    public static void saveOutput (String dest,IndexBuilder iBuilder) {
        try {
            File file = new File(dest);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            //This is the original way of displaying output, but it contains an empty line in the front
            /*
            for (String index : iBuilder.getIndexes()) {
                bw.write("\n"+index);
                System.out.println(index);
            }
            */

            //Not the prettiest way, but it removes the new line in the start of the output
            String[] indexes= iBuilder.getIndexes();
            bw.write(indexes[1]);
            for (int i = 2; i < indexes.length; i++ ){
                bw.write("\n"+indexes[i]);
                System.out.println(indexes[i]);
            }


            bw.close();
            fw.close();
            System.out.println("\nIndexes saved to "+dest+" successfully.");

        } catch (IOException e) {
            System.out.println("IO error occurred. Terminating application.");
        }
    }
}
