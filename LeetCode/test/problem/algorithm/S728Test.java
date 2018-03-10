package problem.algorithm;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class S728Test {

    @Test
    public void example1() {
        assertEquals(new S728().selfDividingNumbers(1, 22),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22));
    }
}