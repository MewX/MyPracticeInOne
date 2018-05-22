package problem.algorithm;

import org.testng.asserts.SoftAssert;

public class TestBase {
    protected void assert2DArrayEqual(final int[][] actual, final int[][] expected) {
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < actual.length; i ++) {
            softAssert.assertEquals(actual[i], expected[i], String.format("Row id %d not match", i));
        }
        softAssert.assertAll();
    }
}
