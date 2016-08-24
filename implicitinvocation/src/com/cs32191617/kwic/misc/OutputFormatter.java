package com.cs32191617.kwic.misc;

import java.io.*;
import java.util.List;

/**
 * Created by zixian on 8/24/16.
 *
 * Defines the output formatter component
 */
public class OutputFormatter implements Closeable {
    private PrintWriter writer;

    public OutputFormatter(){
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    /**
     * Prints the given message
     * @param msg The message to print
     */
    public void printMessage(String msg){
        this.writer.println(msg);
        this.writer.flush();
    }

    /**
     * Writes the given list of strings to the output
     * @param list The list of strings to be written
     */
    public void writeStringList(List<String> list){
        list.forEach(keyword -> this.writer.println(keyword));
        this.writer.flush();
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }
}
