package problem.algorithm;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class S690Test {

    @Test
    public void testExample1() {
        S690.Employee e1 = new S690.Employee();
        e1.id = 1;
        e1.importance = 5;
        e1.subordinates = Arrays.asList(2, 3);

        S690.Employee e2 = new S690.Employee();
        e2.id = 2;
        e2.importance = 3;
        e2.subordinates = Collections.emptyList();

        S690.Employee e3 = new S690.Employee();
        e3.id = 3;
        e3.importance = 3;
        e3.subordinates = Collections.emptyList();

        assertEquals(new S690().getImportance(Arrays.asList(e3, e2, e1), 1), 11);
    }
}