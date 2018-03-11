package problem.algorithm;

import java.util.LinkedList;

public class S682 {
    public int calPoints(String[] ops) {
        LinkedList<Integer> list = new LinkedList<>();
        int temp;
        for (String op : ops) {
            switch (op) {
                case "+":
                    temp = list.getLast() + list.get(list.size() - 2);
                    list.addLast(temp);
                    break;

                case "D":
                    list.addLast(list.getLast() * 2);
                    break;

                case "C":
                    list.removeLast();
                    break;

                default:
                    // integer
                    list.addLast(Integer.valueOf(op));
            }
        }

        int sum = 0;
        for (Integer i : list) sum += i;
        return sum;
    }
}
