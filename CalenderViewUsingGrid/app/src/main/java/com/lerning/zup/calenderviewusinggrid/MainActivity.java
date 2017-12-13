package com.lerning.zup.calenderviewusinggrid;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout mTabLayout;
    View vLeft;
    View vRight;
    View vFake;
    String[] mMonths = {"Dezembro", "Janeiro", "Fevereiro", "Março"};
    int mCurrentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabLayout);
        vLeft = findViewById(R.id.vLeft);
        vRight = findViewById(R.id.vRight);
        vFake = findViewById(R.id.fakeView);
        setUpViews();

        mViewPager.setCurrentItem(mCurrentItem);
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
                //Construtor para mudar a cor...
                //MyCalendarView myCalendarView = new MyCalendarView(MainActivity.this, mDates[position],R.color.colorPurple);
                container.addView(myCalendarView);
                return myCalendarView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
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

        setupTabs();
        vLeft.setOnClickListener(v ->
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition() - 1));
        vRight.setOnClickListener(v ->
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition() + 1));
    }

    private void setupTabs() {
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mMonths.length; i++) {
            setUpItemTabs(i);
            setUpItemSpace(i);
        }

        mViewPager.setCurrentItem(mCurrentItem);
    }

    private void setUpItemTabs(int index) {
        TabLayout.Tab mTab = mTabLayout.getTabAt(index);
        mTab.setCustomView(null);
        mTab.setCustomView(getCustomTab(mMonths[index], "7 dias"));
    }

    private void setUpItemSpace(int index) {
        int distanceTab = getWindowManager().getDefaultDisplay().getWidth() / 4;
        View tab = ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(index);
        tab.setMinimumWidth(getWindowManager().getDefaultDisplay().getWidth() / 2);
        tab.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tab.requestLayout();
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
        p.setMargins(index == 0 ? distanceTab : 0, 0, index == mTabLayout.getTabCount() - 1 ? distanceTab : 0, 0);
    }

    @NonNull
    private View getCustomTab(String title, String usedDays) {
        LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
        View mCustomTab = mInflater.inflate(R.layout.item_tab_view, null);
        TextView mTitle = mCustomTab.findViewById(R.id.month_text);
        TextView mUsedDays = mCustomTab.findViewById(R.id.usedDays_text);
        mTitle.setText(title);
        mUsedDays.setText(usedDays);

        return mCustomTab;
    }

    private void setUpViews() {
        int height = dpToPx(60);
        int centerViewSize = getWindowManager().getDefaultDisplay().getWidth() / 2;
        RelativeLayout.LayoutParams layoutParams;
        layoutParams = new RelativeLayout.LayoutParams(centerViewSize, height);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        vFake.setLayoutParams(layoutParams);

        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        layoutParams.setMarginStart(centerViewSize + (centerViewSize / 2));
        vRight.setLayoutParams(layoutParams);

        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
        layoutParams.setMarginEnd(centerViewSize + (centerViewSize / 2));
        vLeft.setLayoutParams(layoutParams);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
