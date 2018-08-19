package problem.algorithm;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Node;

import java.util.Arrays;

public class S590Test {

    @Test
    public void testPostorder() {
        Node testing = new Node(1, Arrays.asList(
                new Node(3, Arrays.asList(
                        new Node(5, null),
                        new Node(6, null)
                )),
                new Node(2, null),
                new Node(4, null)
        ));

        Assert.assertEquals(Arrays.asList(5, 6, 3, 2, 4, 1), new S590().postorder(testing));
    }
}