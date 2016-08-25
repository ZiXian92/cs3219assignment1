package com.cs32191617.kwic;

import com.cs32191617.kwic.exceptions.InputFileException;
import com.cs32191617.kwic.exceptions.OutputFileException;
import com.cs32191617.kwic.keywordbuilder.KeywordBuilder;
import com.cs32191617.kwic.misc.InputReader;
import com.cs32191617.kwic.misc.OutputFormatter;
import com.cs32191617.kwic.pubsub.ActionCreator;
import com.cs32191617.kwic.pubsub.Dispatcher;

import java.io.IOException;
import java.util.List;

public class Main {

    // Prompt messages
    private static final String MSG_PROMPT_IGNORE_LIST = "Enter list of words to ignore(comma-separated):";
    private static final String MSG_PROMPT_INPUT = "Enter phrases, 1 on each line. Terminate by entering Ctrl+D";

    // Error messages
    private static final String MSG_ERR_INVALID_FORMAT = "Invalid command line format. Format should be java -jar <jar_name> <input_file_path> <output_file_path>";
    private static final String MSG_ERR_INPUT_FILE_ERROR = "Error opening file for input";
    private static final String MSG_ERR_OUTPUT_FILE_ERROR = "Error opening file for output";

    public static void main(String[] args) {

        // Check if argument format is valid
        if(args.length==1){
            logErrorToConsole(MSG_ERR_INVALID_FORMAT);
            System.exit(0);
        }

        boolean isFileIO = args.length>0;
        Dispatcher dispatcher = Dispatcher.getInstance();
        KeywordBuilder keywordBuilder;
        String input;

        try(InputReader reader = args.length==0? new InputReader(): new InputReader(args[0]);
            OutputFormatter writer = args.length==0? new OutputFormatter(): new OutputFormatter(args[1])){

            // Read in words to ignore
            if(!isFileIO) writer.writeToConsole(MSG_PROMPT_IGNORE_LIST);
            input = reader.readInputLine();
            if(input!=null){
                // Process the words to ignore by creating the keyword builder
                keywordBuilder = new KeywordBuilder(input);

                // Prompt user to input phrases
                if(!isFileIO) writer.writeToConsole(MSG_PROMPT_INPUT);

                // Keep reading inputs and generating indexes
                input = reader.readInputLine();
                while(input!=null){
                    dispatcher.dispatch(ActionCreator.handleKwicInput(input));
                    input = reader.readInputLine();
                }

                // Print output or dispatch an action to do so
                List<String> keywords = keywordBuilder.getKeywords();
                writer.writeStringList(keywords);
            }
        } catch(InputFileException e){
            logErrorToConsole(MSG_ERR_INPUT_FILE_ERROR);
        } catch(OutputFileException e){
            logErrorToConsole(MSG_ERR_OUTPUT_FILE_ERROR);
        } catch(IOException e){
            logErrorToConsole("IO error occurred. Terminating application.");
        }
    }

    /**
     * Logs the given message to console.
     * @param msg
     */
    public static void logErrorToConsole(String msg){
        System.out.println(msg);
    }
}
