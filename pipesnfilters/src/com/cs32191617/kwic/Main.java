package com.cs32191617.kwic;

import com.cs32191617.kwic.components.IgnoreListParser;
import com.cs32191617.kwic.exceptions.InputFileException;
import com.cs32191617.kwic.exceptions.OutputFileException;
import com.cs32191617.kwic.components.InputReader;
import com.cs32191617.kwic.components.OutputFormatter;
import com.cs32191617.kwic.indexbuilder.IndexBuilder;

import java.io.IOException;


public class Main {
    private static final String MSG_PROMPT_IGNORE_LIST = "Enter list of words to ignore(comma-separated):";
    private static final String MSG_PROMPT_INPUT = "Enter phrases, 1 on each line. Terminate by entering Ctrl+D";

    // Error messages
    private static final String MSG_ERR_INVALID_FORMAT = "Invalid command line format. Format should be java -jar <jar_name> <input_file_path> <output_file_path>";
    private static final String MSG_ERR_INPUT_FILE_ERROR = "Error opening file for input";
    private static final String MSG_ERR_OUTPUT_FILE_ERROR = "Error opening file for output";

    public static void main(String[] args) {

        // Check if argument format is valid
        if (args.length == 1) {
            logErrorToConsole(MSG_ERR_INVALID_FORMAT);
            System.exit(0);
        }

        boolean isFileIO = args.length > 0;
        String input;

        try (InputReader reader = args.length == 0 ? new InputReader() : new InputReader(args[0]);
             OutputFormatter writer = args.length == 0 ? new OutputFormatter() : new OutputFormatter(args[1])) {

            // Read in words to ignore
            if (!isFileIO) writer.writeToConsole(MSG_PROMPT_IGNORE_LIST);
            input = reader.readInputLine();
            if (input != null) {
                // Process the words to ignore by creating the keyword builder
                IndexBuilder iBuilder = new IndexBuilder(IgnoreListParser.generateIgnoreList(input));

                // Prompt user to input phrases
                if (!isFileIO) writer.writeToConsole(MSG_PROMPT_INPUT);

                // Keep reading inputs and generating indexes
                input = reader.readInputLine();
                while (input != null) {
                    iBuilder.generateAndAddIndexes(input);
                    input = reader.readInputLine();
                }

                // Print output or dispatch an action to do so
                String[] keywords= iBuilder.getIndexes();
                writer.writeStringArr(keywords);
            }
        } catch (InputFileException e) {
            logErrorToConsole(MSG_ERR_INPUT_FILE_ERROR);
        } catch (OutputFileException e) {
            logErrorToConsole(MSG_ERR_OUTPUT_FILE_ERROR);
        } catch (IOException e) {
            logErrorToConsole("IO error occurred. Terminating application.");
        }
    }

    /**
     * Logs the given message to console.
     *
     * @param msg
     */
    public static void logErrorToConsole(String msg) {
        System.out.println(msg);
    }
}
