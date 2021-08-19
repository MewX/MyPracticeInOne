package problem.algorithm;

import java.util.*;

public class S884 {

    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> recorder = new HashMap<>();
        for (String str : A.split(" ")) {
            if (recorder.containsKey(str)) {
                recorder.put(str, recorder.get(str) + 1);
            } else {
                recorder.put(str, 1);
            }
        }

        for (String str : B.split(" ")) {
            if (recorder.containsKey(str)) {
                recorder.put(str, recorder.get(str) + 1);
            } else {
                recorder.put(str, 1);
            }
        }

        List<String> list = new ArrayList<>();
        for (String key : recorder.keySet()) {
            if (recorder.get(key) == 1) {
                list.add(key);
            }
        }

        return list.toArray(new String[0]);
    }
}
