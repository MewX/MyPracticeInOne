package problem.algorithm;

import java.util.ArrayList;
import java.util.List;

public class S728 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i ++) {
            if (legit(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean legit(final int num) {
        final String str = Integer.toString(num);
        for (int i = 0; i < str.length(); i ++) {
            final int n = str.charAt(i) - '0';
            if (n == 0 || num % n != 0)
                return false;
        }
        return true;
    }
}
