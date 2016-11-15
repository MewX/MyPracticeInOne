package org.mewx.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a1700831 on 17/08/16.
 */
public class PartySeats {

    private class People {
        public String name;
        public boolean isBoy; // girl - false
        public People(String name, String gender) {
            this.name = name;
            isBoy = gender.equals("boy");
        }
    }

    public String[] seating(String[] attendees) {
        if (attendees.length % 4 != 0) return new String[0];

        // parse data, gender should equal
        List<People> girls = new ArrayList<>(), boys = new ArrayList<>();
        for (String temp : attendees) {
            int sep = temp.lastIndexOf(" ");
            People people = new People(temp.substring(0, sep), temp.substring(sep + 1));

            if (people.isBoy) boys.add(people);
            else girls.add(people);
        }

        // test gender
        if (boys.size() != girls.size()) return new String[0];

        // sort all
        boys.sort((a, b) -> a.name.compareTo(b.name));
        girls.sort((a, b) -> a.name.compareTo(b.name));

        // test out put
//        for (People p : boys) {
//            System.out.println(p.name);
//        }

        // arrange seats
        List<String> result = new ArrayList<>();
        int mid = boys.size() / 2;
        result.add("HOST");
        for (int i = 0; i < mid; i ++) {
            result.add(girls.get(i).name);
            result.add(boys.get(i).name);
        }
        result.add("HOSTESS");
        for (int i = mid; i < boys.size(); i ++) {
            result.add(boys.get(i).name);
            result.add(girls.get(i).name);
        }

        return result.toArray(new String[result.size()]);
    }
}