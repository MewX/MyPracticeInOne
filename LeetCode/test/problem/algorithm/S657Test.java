package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class S657Test {

    @Test
    public void example1() {
        assertEquals(new S657().judgeCircle("UD"), true);
    }

    @Test
    public void example2() {
        assertEquals(new S657().judgeCircle("LL"), false);
    }
}