package com.lerning.zup.calenderviewusinggrid;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by ZUP on 13/12/2017.
 */

public class OverTab extends RelativeLayout {
    private View mCenterView, mRightView, mLeftView;
    private int mCenterViewSize;
    private int mHeight;
    private ClickCallBack mClickCallBack;

    public OverTab(Context context) {
        super(context);
        init();
    }

    public OverTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OverTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public OverTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setListener(ClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
    }

    public void init() {
        View layout = inflate(getContext(), R.layout.over_tab_view, this);
        mLeftView = layout.findViewById(R.id.vLeft);
        mRightView = layout.findViewById(R.id.vRight);
        mCenterView = layout.findViewById(R.id.vCenter);
        mHeight = dpToPx(60);
        //setLayoutRules();

        mLeftView.setOnClickListener(v -> mClickCallBack.clickPrevious());

        mRightView.setOnClickListener(v -> mClickCallBack.clickNext());
    }

    public void setLayoutRules() {
        RelativeLayout.LayoutParams layoutParams;
        layoutParams = new RelativeLayout.LayoutParams(mCenterViewSize, mHeight);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mCenterView.setLayoutParams(layoutParams);

        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_END, mCenterView.getId());
        mLeftView.setLayoutParams(layoutParams);

        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_START, mCenterView.getId());
        mRightView.setLayoutParams(layoutParams);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public interface ClickCallBack {
        void clickPrevious();

        void clickNext();
    }
}
