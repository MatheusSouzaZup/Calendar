package com.lerning.zup.calenderviewusinggrid;

/**
 * Created by ZUP on 29/11/2017.
 */

public class Day {

    String day;
    boolean usedDay;
    boolean enable = true;

    public Day(String day, boolean usedDay) {
        this.day = day;
        this.usedDay = usedDay;
    }

    public Day(String day, boolean usedDay, boolean enable) {
        this.day = day;
        this.usedDay = usedDay;
        this.enable = enable;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day1 = (Day) o;

        return day != null ? day.equals(day1.day) : day1.day == null;
    }
}
