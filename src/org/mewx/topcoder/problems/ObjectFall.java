package org.mewx.topcoder.problems;

import java.util.ArrayList;

/**
 * Created by a1700831 on 3/08/16.
 */
public class ObjectFall {
    private class Obstacle {
        public int y, x1, x2;
    }

    public int howLong(int x, int y, String[] obstacles) {
        int dropTime = 0;

        // fetch the values
        ArrayList<Obstacle> obs = new ArrayList<>();
        for (int i = 0; i < obstacles.length; i ++) {
            String[] temp = obstacles[i].split(" ");
            Obstacle obtemp = new Obstacle();
            obtemp.y = Integer.valueOf(temp[0]);
            obtemp.x1 = Integer.valueOf(temp[1]);
            obtemp.x2 = Integer.valueOf(temp[2]);
            obs.add(obtemp);
        }

        /* // DEPRECATED CODES
        // do calculations
        // sort by 'y' (note: may be more than one records for one 'y')
        // then just brute force 'y' (OBVIOUSLY OPTIMIZABLE)
        Collections.sort(obs, (o1, o2) -> o2.y - o1.y); // from high to low, by 'y'

        // test output
//        for (Obstacle o : obs) {
//            System.out.println(o.y + " " + o.x1 + " " + o.x2);
//        }
*/

        // use brute force
        for (; y > 0; y --, dropTime ++) { // if y == 0, it's already landed

            // judge if in obstacle
//            boolean noObs = false;
//            out: while (!noObs) {
//                noObs = true;
//                for (Obstacle ob : obs) {
//                    if (ob.y == y && ob.x1 <= x && x <= ob.x2) {
//                        // hit the obstacle
//                        dropTime += 5;
//                        x = ob.x2 + 1; // change x, but may be obstacle again
//                        noObs = false;
//                        continue out; // continue this until it's not in obstacle
//                    }
//                }
//            }
            for (Obstacle ob : obs) {
                if (ob.y == y && ob.x1 <= x && x <= ob.x2) {
                    // hit the obstacle
                    dropTime += 5;
                    x = ob.x2; // change x
                }
            }
        }

        return dropTime;
    }
}
