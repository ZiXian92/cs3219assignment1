package com.cs32191617.kwic.misc;

import com.cs32191617.kwic.exceptions.InputFileException;

import java.io.*;

/**
 * Created by zixian on 8/24/16.
 *
 * Defines the input reader component.
 */
public class InputReader implements Closeable {
    private BufferedReader reader;

    /**
     * Creates a new input reader that reads from the console.
     */
    public InputReader(){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Creates new instance of input reader that reads from specified file.
     * @param inputFileName relative path to the input file
     */
    public InputReader(String inputFileName) throws InputFileException {
        try {
            this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        } catch(FileNotFoundException e){
            throw new InputFileException();
        }
    }

    /**
     * Reads a line from the input source
     * @return A line of string or null
     */
    public String readInputLine() throws IOException {
        return this.reader.readLine();
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
