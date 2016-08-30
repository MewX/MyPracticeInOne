package org.mewx.topcoder.problems;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by MewX on 8/30/2016.
 */
public class SpecialDay {

    public int howMany(String weekday, int which, String month, int day) throws ParseException {
        final int wd = getWeekNumber(weekday);
        final int m = getMontNumber(month);
//        if ((which - 1) * 7 >= day || which * 7 < day) return 0;

        // content
        int times = 0;
        Calendar c = Calendar.getInstance();
        for (int i = 2000; i <= 2100; i ++) {
            c.set(i, m, 1);
            int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

            // judge if which-th weekday
            int count = 0;
            int fesDay;
            for (fesDay = 1; fesDay <= dayOfMonth; fesDay ++) {
                c.set(i, m, fesDay);
                if (c.get(Calendar.DAY_OF_WEEK) == wd) count ++;
                if (count == which) break;
            }
            // final judge
            if (day == fesDay && fesDay <= dayOfMonth) {
//                System.out.println("fesDay: " + fesDay + "/" + month + "/" + i);
                times ++;
            }
        }
        return times;
    }

    private int getWeekNumber(final String week) {
        // from 1
        switch (week) {
            case "SUNDAY": return Calendar.SUNDAY;
            case "MONDAY": return Calendar.MONDAY;
            case "TUESDAY": return Calendar.TUESDAY;
            case "WEDNESDAY": return Calendar.WEDNESDAY;
            case "THURSDAY": return Calendar.THURSDAY;
            case "FRIDAY": return Calendar.FRIDAY;
            case "SATURDAY": return Calendar.SATURDAY;
        }
        System.out.println("error in week: " + week);
        return 0;
    }

    private int getMontNumber(final String month) {
        // from 1
        switch (month) {
            case "JANUARY": return Calendar.JANUARY;
            case "FEBRUARY": return Calendar.FEBRUARY;
            case "MARCH": return Calendar.MARCH;
            case "APRIL": return Calendar.APRIL;
            case "MAY": return Calendar.MAY;
            case "JUNE": return Calendar.JUNE;
            case "JULY": return Calendar.JULY;
            case "AUGUST": return Calendar.AUGUST;
            case "SEPTEMBER": return Calendar.SEPTEMBER;
            case "OCTOBER": return Calendar.OCTOBER;
            case "NOVEMBER": return Calendar.NOVEMBER;
            case "DECEMBER": return Calendar.DECEMBER;
        }
        System.out.println("error in month: " + month);
        return 0;
    }
}
