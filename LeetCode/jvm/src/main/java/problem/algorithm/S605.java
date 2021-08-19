package problem.algorithm;

public class S605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = 0;

        int i = 0;
        while (i < flowerbed.length && flowerbed[i] == 0) i ++;
        // make i = 1
        max += i/ 2;
        if (i == flowerbed.length) max = (flowerbed.length + 1) / 2;

        while (i < flowerbed.length) {
            // from having flower
            while (i < flowerbed.length && flowerbed[i] == 1) i++;
            // make i = 0
            int beg1 = i;

            while (i < flowerbed.length && flowerbed[i] == 0) i++;
            // make i = 1
            if (i == flowerbed.length) i ++;
            max += (i - beg1 - 1) / 2;
        }

        return max >= n;
    }
}
