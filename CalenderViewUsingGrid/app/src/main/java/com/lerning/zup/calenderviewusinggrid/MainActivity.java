package com.lerning.zup.calenderviewusinggrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarAdapter mAdapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.calendar);
        MyCalendar myCalendar = setDate(new GregorianCalendar(2017,12-1,1));
        mAdapter = new CalendarAdapter(getMonthConfigurated(myCalendar));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    private List<Integer> getUsedDays() {
        List<Integer> mList = new ArrayList<>();
        mList.add(1);
        mList.add(5);
        mList.add(9);
        mList.add(13);
        mList.add(21);
        return mList;
    }
    public MyCalendar setDate(Calendar calendar) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setFirstDay(calendar.get(Calendar.DAY_OF_WEEK));
        myCalendar.setMonthSize(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        myCalendar.setUsedDays(getUsedDays());
        return myCalendar;
    }
    public List<Day> getMonthConfigurated(MyCalendar myCalendar) {
        List<Day> mList = new ArrayList<>();
        int listSize;
        Integer mDays = 1;

        listSize = myCalendar.getFirstDay() + myCalendar.getMonthSize();

        for(Integer i = 1; i <listSize; i++) {
            if(i < myCalendar.getFirstDay()) {
                mList.add(null);
            }
            else {
                if(myCalendar.getUsedDays().contains(mDays))
                    mList.add(new Day(mDays.toString(),true));
                else {
                    mList.add(new Day(mDays.toString(),false));
                }
                mDays++;
            }
        }
        return mList;
    }
}
