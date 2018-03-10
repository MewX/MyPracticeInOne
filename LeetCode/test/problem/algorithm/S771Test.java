package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class S771Test {

    @Test
    public void example1() throws Exception {
        Assert.assertEquals(new S771().numJewelsInStones("aA", "aAAbbbb"), 3);
    }

    @Test
    public void example2() throws Exception {
        Assert.assertEquals(new S771().numJewelsInStones("z", "ZZ"), 0);
    }
}