package org.mewx.topcoder.problems;

/**
 * Created by MewX on 8/16/2016.
 */
public class SquareOfDigits {
    public int getMax(String[] data) {
        int result = 1;
        // brute-force
        for (int y = 0; y < data.length; y ++) { // top-left corner
            for (int x = 0; x < data[0].length() - 1; x ++) {

                // set right edge position
                for (int xr = x + 1; xr < data[0].length(); xr ++) { // right - x
                    // so, other points are defined as well
                    int distance = xr - x;
                    if (y + distance >= data.length) break;

                    // compare
                    if (data[y].charAt(x) == data[y].charAt(xr) && data[y].charAt(xr) == data[y+distance].charAt(x)
                            && data[y+distance].charAt(x) == data[y+distance].charAt(xr)) {
                        if (distance + 1 > result) result = distance + 1;
                    }
                }
            }
        }
        return result * result;
    }
}
