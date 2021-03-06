/**
 * Created by zixian on 8/19/16.
 */

import com.cs32191617.kwic.keywordbuilder.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FilterTest {
    @Test
    public void testFilter(){
        String[] ignoreList = {"a", "the", "and", "of"};
        Filter filter = new Filter(Arrays.asList(ignoreList));
        Assert.assertFalse("Filter should return false", filter.isKeywordIndex("The Lord of the Rings"));
        Assert.assertTrue("Filter should return true", filter.isKeywordIndex("Harry Potter and the Prisoner of Azkaban"));
    }
}
