package com.lerning.zup.calenderviewusinggrid;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ZUP on 29/11/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    List<Day> mList;
    OnCalenderListener mListener;

    public CalendarAdapter(Calendar calendar) {
        mList = getMonthConfigurated(calendar);
    }

    public CalendarAdapter(Calendar calendar, OnCalenderListener listener) {
        mList = getMonthConfigurated(calendar);
        mListener = listener;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_calendar, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CalendarViewHolder holder, final int position) {
        Day day = mList.get(position);
        holder.onBind(day);
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    private MyCalendar setUpValidyDate(Calendar calendar) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setFirstDay(calendar.get(Calendar.DAY_OF_WEEK));
        myCalendar.setMonthSize(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        myCalendar.setUsedDays(getUsedDays());
        return myCalendar;
    }

    private List<Day> getMonthConfigurated(Calendar calendar) {
        MyCalendar myCalendar = setUpValidyDate(calendar);
        List<Day> mList = new ArrayList<>();
        fillWithEmptyDay(myCalendar, mList);
        createUsefulDays(myCalendar, mList);
        return mList;
    }

    private void createUsefulDays(MyCalendar myCalendar, List<Day> mList) {
        for (int i = 1; i <= myCalendar.getMonthSize(); i++) {
            boolean isUsedDay = myCalendar.getUsedDays().contains(i);
            mList.add(new Day(i + "", isUsedDay, true));
        }
    }

    private void fillWithEmptyDay(MyCalendar myCalendar, List<Day> mList) {
        for (int i = 0; i < myCalendar.getFirstDay() -1; i++){
            mList.add(null);
        }
    }

    private List<Integer> getUsedDays() {
        List<Integer> mList = new ArrayList<>();
        mList.add(1);
        mList.add(4);
        mList.add(10);
        mList.add(13);
        mList.add(21);
        return mList;
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDay;
        ImageView mIvDot;
        Day mDay;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.calendar_day);
            mIvDot = itemView.findViewById(R.id.calendar_dot);
        }

        public void onBind(final Day day) {
            if (day == null) {
                mTvDay.setVisibility(View.INVISIBLE);
                mIvDot.setVisibility(View.INVISIBLE);
                return;
            }

            mDay = day;
            mTvDay.setText(day.getDay());
            if (day.isUsedDay()) {
                setupUsedDay(day);
            }

            if (! day.isEnable()) {
                mTvDay.setAlpha(0.2f);
                mIvDot.setAlpha(0.2f);
            }
        }

        private void setupUsedDay(final Day day) {
            mIvDot.setVisibility(View.VISIBLE);
            mTvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorBlack));
            itemView.setOnClickListener(v -> {
                /*for (Day d : mList){
                    if (d == null || day.equals(d)){
                        continue;
                    }

                    d.setEnable(false);
                }
                notifyDataSetChanged();*/

                if (mListener != null){
                    mListener.onClickItemDay(v, day);
                }
            });
        }
    }

    public interface OnCalenderListener {
        void onClickItemDay(View view, Day myCalendar);
    }
}
