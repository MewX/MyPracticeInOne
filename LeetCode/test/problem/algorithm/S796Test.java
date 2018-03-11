package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S796Test {

    @Test
    public void example1() {
        assertEquals(new S796().rotateString("abcde", "cdeab"), true);
    }

    @Test
    public void example2() {
        assertEquals(new S796().rotateString("abcde", "abced"), false);
    }
}