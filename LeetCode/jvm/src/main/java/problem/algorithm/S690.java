package problem.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S690 {
    static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return recurse(map, id);
    }

    private int recurse(Map<Integer, Employee> map, int id) {
        Employee e = map.get(id);
        int sum = e.importance;
        if (e.subordinates != null) {
            for (Integer ie : e.subordinates) {
                sum += recurse(map, ie);
            }
        }
        return sum;
    }
}
