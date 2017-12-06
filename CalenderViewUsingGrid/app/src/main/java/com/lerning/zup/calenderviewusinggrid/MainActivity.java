package com.lerning.zup.calenderviewusinggrid;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    CalendarAdapter mAdapter;
    RecyclerView mRecyclerView;
    View mIvBall;
    View mViewWhite;

    private float posYBall;
    private int timeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.calendar);
        mIvBall = findViewById(R.id.ball);
        mViewWhite = findViewById(R.id.v_white);

        int pTop = 100;
        final float pTopPx = dpToPx(pTop);

        GregorianCalendar date = new GregorianCalendar(2018, 2 - 1, 1);

        mAdapter = new CalendarAdapter(date, (view, myCalendar) -> {
            timeAnimation = 650;
            posYBall = (view.getY() + pTopPx) - dpToPx(20);
            float x = view.getX() - dpToPx(10);
            if (x < 0) x = 0;

            mIvBall.setX(x);
            mIvBall.setY(posYBall + dpToPx(40));
            mIvBall.setAlpha(0.1f);

            mIvBall.setVisibility(View.VISIBLE);
            mIvBall.animate()
                    .y(posYBall)
                    .alpha(1)
                    .setDuration(timeAnimation)
                    .setInterpolator(new AnticipateOvershootInterpolator())
                    .start();

            mViewWhite.setVisibility(View.VISIBLE);
            mViewWhite.setClickable(true);
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mViewWhite.setOnClickListener((view) -> {
            mViewWhite.setVisibility(View.INVISIBLE);
            mViewWhite.setClickable(false);

            mIvBall.animate()
                    .y(posYBall + dpToPx(40))
                    .alpha(0f)
                    .setDuration(timeAnimation)
                    .setInterpolator(new AnticipateOvershootInterpolator())
                    .withEndAction(() -> mIvBall.setVisibility(View.INVISIBLE))
                    .start();

        });
    }


    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
