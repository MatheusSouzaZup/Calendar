package com.lerning.zup.calenderviewusinggrid;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);

        mViewPager.setAdapter(new PagerAdapter() {

            GregorianCalendar[] mDates = {
                    new GregorianCalendar(2017, 12 - 1, 1),
                    new GregorianCalendar(2018, 1 - 1, 1),
                    new GregorianCalendar(2018, 2 - 1, 1),
                    new GregorianCalendar(2018, 3 - 1, 1),
            };

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                /*LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.my_calendar, container, false);
                container.addView(layout);
                return layout;*/

                MyCalendarView myCalendarView = new MyCalendarView(MainActivity.this, mDates[position]);
                container.addView(myCalendarView);
                return myCalendarView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public int getCount() {
                return mDates.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
