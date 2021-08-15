package problem.algorithm;

import org.testng.annotations.Test;
import utils.Node;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class S559Test {

    @Test
    public void testMaxDepth() {
        assertEquals(3, new S559().maxDepth(
                new Node(1, Arrays.asList(
                        new Node(3, Arrays.asList(
                                new Node(5, null),
                                new Node(6, null)
                        )),
                        new Node(2, null),
                        new Node(4, null)
                ))
        ));
    }

    @Test
    public void testMaxDepth2() {
        assertEquals(4, new S559().maxDepth(
                new Node(1, Arrays.asList(
                        new Node(3, Arrays.asList(
                                new Node(5, null),
                                new Node(6, null)
                        )),
                        new Node(2, null),
                        new Node(4, Collections.singletonList(
                                new Node(7, Collections.singletonList(
                                        new Node(8, null)
                                ))
                        ))
                ))
        ));
    }
}