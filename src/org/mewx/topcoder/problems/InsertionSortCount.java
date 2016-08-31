package org.mewx.topcoder.problems;

/**
 * Created by a1700831 on 10/08/16.
 */
public class InsertionSortCount {

    public int countMoves(int[] A) {
        int count = 0;

        // insertion sort
        /*
        insertion-sort(A)
           initialize a new empty sequence R
           for each number N in A (in the original order) do:
              determine the index i in R where N should be inserted so that R remains sorted
              move each element in R with index greater than or equal to i to the following index
              set R[i]=N
           return R
         */

        for (int i = 1; i < A.length; i ++) {
            int j = i;
            while (j > 0 && A[j] < A[j - 1]) {
                count ++;
                int temp = A[j-1];
                A[j-1] = A[j];
                A[j] = temp;
                j --;
            }
        }

        return count;
    }
}
