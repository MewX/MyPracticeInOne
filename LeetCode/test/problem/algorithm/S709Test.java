package problem.algorithm;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class S709Test {

    @Test
    public void example1() {
        assertEquals("hello", new S709().toLowerCase("Hello"));
    }

    @Test
    public void example2() {
        assertEquals("here", new S709().toLowerCase("here"));
    }

    @Test
    public void example3() {
        assertEquals("lovely", new S709().toLowerCase("LOVELY"));
    }
}