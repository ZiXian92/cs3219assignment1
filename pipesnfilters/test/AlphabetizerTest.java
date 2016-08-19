/**
 * Created by zixian on 8/19/16.
 */

import com.cs32191617.kwic.components.Alphabetizer;

import org.junit.Assert;
import org.junit.Test;

public class AlphabetizerTest {
    @Test
    public void testAlphabetizer(){
        String[] ignoreList = {"a", "the"};
        String input = "a Quick brown 4ox jumps Over The wall";
        String expected = "a Quick Brown 4ox Jumps Over The Wall";
        Alphabetizer alphabetizer = new Alphabetizer(ignoreList);
        String output = alphabetizer.alphabetize(input);
        Assert.assertEquals("Output should be the same as expected output", output, expected);
    }
}
