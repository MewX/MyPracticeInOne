package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

public class S884Test {

    @Test
    public void testExample1() {
        Assert.assertEquals(new HashSet<>(Arrays.asList("sweet", "sour")),
                new HashSet<>(Arrays.asList(new S884().uncommonFromSentences("this apple is sweet", "this apple is sour"))));
    }

    @Test
    public void testExample2() {
        Assert.assertEquals(new HashSet<>(Arrays.asList("banana")),
                new HashSet<>(Arrays.asList(new S884().uncommonFromSentences("apple apple", "banana"))));
    }

}