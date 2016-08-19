/**
 * Created by zixian on 8/19/16.
 */

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import com.cs32191617.kwic.components.CircularShifter;

public class CircularShifterTest {
    @Test
    public void testShift(){
        CircularShifter shifter = new CircularShifter();
        String[] expected = {"a b c d e f", "b c d e f a", "c d e f a b", "d e f a b c", "e f a b c d", "f a b c d e"};
        List<String> output = shifter.generateIndexes("a  b c   d e f");
        Assert.assertEquals("Output should have same number of indexes as expected output", output.size(), expected.length);
        for(String o: expected){
            Assert.assertTrue("Output should contain all elements of expected output", output.contains(o));
        }
    }
}
