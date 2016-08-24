/**
 * Created by zixian on 8/19/16.
 */

import com.cs32191617.kwic.keywordbuilder.Capitalizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CapitalizerTest {
    @Test
    public void testAlphabetizer(){
        String[] ignoreList = {"a", "the"};
        String input = "a Quick brown 4ox jumps Over The wall";
        String expected = "a Quick Brown 4ox Jumps Over The Wall";
        Capitalizer capitalizer = new Capitalizer(Arrays.asList(ignoreList));
        String output = capitalizer.alphabetize(input);
        Assert.assertEquals("Output should be the same as expected output", output, expected);
    }
}
