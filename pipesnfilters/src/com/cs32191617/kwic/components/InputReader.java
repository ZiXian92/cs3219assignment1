package com.cs32191617.kwic.components;

import com.cs32191617.kwic.exceptions.InputFileException;
import java.io.*;
/**
 * Created by Joan on 26/8/2016.
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
