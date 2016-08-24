/**
 * Created by zixian on 8/19/16.
 */

import com.cs32191617.kwic.keywordbuilder.IgnoreListParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IgnoreListParserTest {
    @Test
    public void testParser() {
        String input = "a, the , in,  by,    of";
        String[] expected = {"a", "the", "in", "by", "of"};
        List<String> output = IgnoreListParser.generateIgnoreList(input);
        Assert.assertArrayEquals(output.toArray(new String[0]), expected);
    }
}
