package com.cs32191617.kwic.misc;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zixian on 8/24/16.
 *
 * Defines the input reader component.
 */
public class InputReader implements Closeable {
    private BufferedReader reader;

    public InputReader(){
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInputLine() throws IOException {
        return this.reader.readLine();
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
