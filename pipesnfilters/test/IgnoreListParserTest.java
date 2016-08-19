/**
 * Created by zixian on 8/19/16.
 */

import com.cs32191617.kwic.components.IgnoreListParser;

import org.junit.Assert;
import org.junit.Test;

public class IgnoreListParserTest {
    @Test
    public void testParser() {
        String input = "a, the , in,  by,    of";
        String[] expected = {"a", "the", "in", "by", "of"};
        String[] output = IgnoreListParser.generateIgnoreList(input);
        Assert.assertArrayEquals(output, expected);
    }
}
