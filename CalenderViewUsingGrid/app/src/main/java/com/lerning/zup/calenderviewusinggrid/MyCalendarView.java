package com.lerning.zup.calenderviewusinggrid;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import java.util.GregorianCalendar;

/**
 * Created by gadal on 06/12/17.
 */

public class MyCalendarView extends RelativeLayout implements CalendarAdapter.OnCalenderListener {

    private GregorianCalendar mDate;

    RecyclerView mRecyclerView;
    View mIvBall;
    View mViewWhite;

    private float posYBall;
    private float initScale = 0.5f;
    private int marginTop = 100;
    private float marginTopPx = 0;
    private int timeAnimation;

    public MyCalendarView(Context context) {
        super(context);
        mDate = new GregorianCalendar(2018, 2 - 1, 1);
        init();
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCalendarView(Context context, GregorianCalendar date) {
        super(context);
        mDate = date;
        init();
    }

    private void init() {
        View layout = inflate(getContext(), R.layout.my_calendar_view, this);

        mRecyclerView = layout.findViewById(R.id.calendar);
        mIvBall = layout.findViewById(R.id.ball);
        mViewWhite = layout.findViewById(R.id.v_white);

        marginTopPx = dpToPx(marginTop);

        CalendarAdapter mAdapter = new CalendarAdapter(mDate, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClickItemDay(View view, Day myCalendar) {
        timeAnimation = 650;
        posYBall = marginTopPx + view.getY() - dpToPx(20);
        float x = view.getX() - dpToPx(10);
        if (x < 0) x = 0;

        animationIN(x);

        mViewWhite.setVisibility(View.VISIBLE);
        mViewWhite.setClickable(true);

        animationOut();
    }

    private void animationOut() {
        mViewWhite.setOnClickListener((v) -> {
            mViewWhite.setVisibility(View.INVISIBLE);
            mViewWhite.setClickable(false);

            mIvBall.animate()
                    .y(posYBall)
                    .alpha(0f)
                    .scaleX(initScale)
                    .scaleY(initScale)
                    .setDuration(timeAnimation)
                    .setInterpolator(new AnticipateOvershootInterpolator())
                    .withEndAction(() -> mIvBall.setVisibility(View.INVISIBLE))
                    .start();

        });
    }

    private void animationIN(float x) {
        mIvBall.setX(x);
        mIvBall.setY(posYBall + dpToPx(40));
        mIvBall.setAlpha(0f);
        mIvBall.setScaleY(initScale);
        mIvBall.setScaleX(initScale);

        mIvBall.setVisibility(View.VISIBLE);
        mIvBall.animate()
                .y(posYBall - dpToPx(40))
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .setDuration(timeAnimation)
                .setInterpolator(new OvershootInterpolator())
                .start();
    }
}
