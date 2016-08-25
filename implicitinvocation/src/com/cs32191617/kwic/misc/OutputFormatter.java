package com.cs32191617.kwic.misc;

import com.cs32191617.kwic.exceptions.OutputFileException;

import java.io.*;
import java.util.List;

/**
 * Created by zixian on 8/24/16.
 *
 * Defines the output formatter component
 */
public class OutputFormatter implements Closeable {
    private PrintWriter writer, consoleWriter;

    /**
     * Creates a new output formatter that writes to the console.
     */
    public OutputFormatter(){
        this.consoleWriter = this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    /**
     * Creates a new output formatter that writes to the specified file.
     * Any missing directories in the file path will be created.
     * @param outputFileName Relative path to the output file
     */
    public OutputFormatter(String outputFileName) throws OutputFileException {
        try {
            File oFile = new File(outputFileName);
            oFile.getParentFile().mkdirs();
            oFile.createNewFile();
            this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(oFile))));
            this.consoleWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        } catch(Exception e){
            throw new OutputFileException();
        }
    }

    /**
     * Prints the given message to console
     * @param msg The message to print
     */
    public void writeToConsole(String msg){
        this.consoleWriter.println(msg);
        this.consoleWriter.flush();
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
        if(this.consoleWriter!=this.writer) this.consoleWriter.close();
    }
}
