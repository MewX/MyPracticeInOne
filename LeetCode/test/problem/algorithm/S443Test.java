package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S443Test {

    @Test
    public void ex1() {
        char[] in = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        assertEquals(new S443().compress(in), 6);
        assertEquals(
                String.valueOf(in).substring(0, 6),
                String.valueOf(new char[]{'a', '2', 'b', '2', 'c', '3'})
        );
    }

    @Test
    public void ex2() {
        char[] in = new char[]{'a'};
        assertEquals(new S443().compress(in), 1);
        assertEquals(String.valueOf(in), String.valueOf(new char[]{'a'}));
    }

    @Test
    public void ex3() {
        char[] in = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        assertEquals(new S443().compress(in), 4);
        assertEquals(
                String.valueOf(in).substring(0, 4),
                String.valueOf(new char[]{'a', 'b', '1', '2'})
        );
    }


    @Test
    public void cus() {
        char[] in = new char[]{'a','a','a','b','b','a','a'};
        assertEquals(new S443().compress(in), 6);
        assertEquals(
                String.valueOf(in).substring(0, 6),
                String.valueOf(new char[]{'a', '3', 'b', '2', 'a', '2'})
        );
    }
}