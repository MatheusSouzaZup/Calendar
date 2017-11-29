package com.lerning.zup.calenderviewusinggrid;

/**
 * Created by ZUP on 29/11/2017.
 */

public class Day {
    String day;
    boolean usedDay;

    public Day(String day, boolean usedDay) {
        this.day = day;
        this.usedDay = usedDay;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isUsedDay() {
        return usedDay;
    }

    public void setUsedDay(boolean usedDay) {
        this.usedDay = usedDay;
    }
}
